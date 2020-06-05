/*
 * Copyright (c) 2020. Jason Burgess
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.introspectsoft.cobilling

import android.app.Activity
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.withContext
import ml.introspectsoft.cobilling.extensions.toSha256
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Kotlin coroutine wrappers for the Google Play Billing library
 *
 * * Google Play Billing v2.2.1
 * * coroutines v2.3.0-alpha03
 *
 * @param[activity] the activity we're running on
 * @param[useDispatcher] an optional context to run on (Default: Dispatchers.IO)
 */
@ExperimentalCoroutinesApi
class CoBilling(private val activity: Activity, useDispatcher: CoroutineDispatcher? = null) {
    companion object {
        // Number of objects to buffer in purchasesUpdated and other channels
        const val BUFFER_SIZE = 100
    }

    private var billingClient: BillingClient? = null
    private val dispatcher: CoroutineDispatcher = useDispatcher ?: Dispatchers.IO

    val purchasesUpdated = BroadcastChannel<Purchase.PurchasesResult>(BUFFER_SIZE)

    /**
     * Get purchases information from play store and triggers callback like it's coming from
     * onPurchasesUpdated().
     *
     * Should be triggered when an activity loads to handle new purchases.
     *
     * @param[skuType] SKU type to check for (INAPP or SUBS)
     */
    suspend fun checkPurchases(skuType: String) {
        val purchases = getPurchased(skuType)
        if (!purchasesUpdated.isClosedForSend) {
            purchasesUpdated.offer(purchases)
        }
    }

    /**
     * Get all of the InApp purchases that have taken place already
     *
     * @return PurchasesResult(BillingResult, List<Purchases>?)
     */
    suspend fun getPurchasedInApps(): Purchase.PurchasesResult =
            getPurchased(BillingClient.SkuType.INAPP)

    /**
     * Get all of the subscription purchases that have taken place already
     *
     * @return PurchasesResult(BillingResult, List<Purchases>?)
     */
    suspend fun getPurchasedSubscriptions(): Purchase.PurchasesResult =
            getPurchased(BillingClient.SkuType.SUBS)

    /**
     * Queries for a list of in app products by SKU
     *
     * @param skuIds the SKU IDs to query. It should contain at least one ID.
     * @return List of products
     */
    suspend fun queryInAppPurchases(vararg skuIds: String) = query(
            BillingClient.SkuType.INAPP, skuIds.toList()
    )

    /**
     * Queries for a list of subscription products by SKU
     *
     * @param skuIds the SKU IDs to query. It should contain at least one ID.
     * @return List of products
     */
    suspend fun querySubscriptions(vararg skuIds: String) = query(
            BillingClient.SkuType.SUBS, skuIds.toList()
    )

    /**
     * Acknowledge the purchase
     * Purchases not acknowledged or consumed after 3 days are refunded.
     *
     * @param[purchased] the purchase to acknowledge
     * @return BillingResult
     */
    suspend fun acknowledgePurchase(purchased: Purchase): BillingResult = withContext(dispatcher) {
        val result = connect()
        if (result.responseCode != BillingResponseCode.OK) {
            return@withContext result
        }

        val params =
                AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchased.purchaseToken)
                        .build()
        suspendCoroutine { it: Continuation<BillingResult> ->
            billingClient?.acknowledgePurchase(params) { ackResult -> it.resume(ackResult) }
        }
    }

    /**
     * Consumes the in app purchase.
     * Purchases not acknowledged or consumed after 3 days are refunded.
     *
     * @param[purchased] the purchase to consume
     * @return ConsumeResult(BillingResult, purchaseToken : String)
     */
    suspend fun consumePurchase(purchased: Purchase): ConsumeResult {
        return withContext(dispatcher) {
            val result = connect()
            if (result.responseCode != BillingResponseCode.OK) {
                return@withContext ConsumeResult(result, "")
            }

            val params =
                    ConsumeParams.newBuilder().setPurchaseToken(purchased.purchaseToken).build()
            suspendCoroutine { it: Continuation<ConsumeResult> ->
                billingClient?.consumeAsync(params) { billingResult: BillingResult, token: String ->
                    it.resume(ConsumeResult(billingResult, token))
                }

            }
        }
    }

    /**
     * Purchases the given item. You can get an instance of SkuDetails through the [queryInAppPurchases] or
     * [querySubscriptions] method.
     *
     * The values of [accountId] and [profileId] will be hashed to remove personally identifying
     * information. Values are hashed using [String.toSha256] which is included in this library.
     *
     * @param[product] the product to purchase
     * @param[accountId] account id to be sent with the purchase
     * @param[profileId] profile id to be sent with the purchase if your app supports multiple profiles
     */
    suspend fun purchase(
            product: SkuDetails, accountId: String? = null, profileId: String? = null
    ): BillingResult {
        val result = connect()
        if (result.responseCode != BillingResponseCode.OK) {
            return result
        }

        val builder = BillingFlowParams.newBuilder().setSkuDetails(product)
        // Add hashed identifiers to request
        if (!accountId.isNullOrEmpty()) {
            builder.setObfuscatedAccountId(accountId.toSha256())
        }
        if (!profileId.isNullOrEmpty()) {
            builder.setObfuscatedProfileId(profileId.toSha256())
        }
        val params = builder.build()

        return billingClient?.launchBillingFlow(activity, params) ?: BillingResult.newBuilder()
                .setResponseCode(BillingResponseCode.DEVELOPER_ERROR)
                .build()
    }

    /**
     * Base get purchased function to back up the public ones.
     *
     * @param[skuType] INAPP or SUBS to retrieve.
     * @return PurchasesResult(BillingResult, List<Purchases>?)
     */
    private suspend fun getPurchased(
            skuType: String
    ): Purchase.PurchasesResult {
        val result = connect()
        if (result.responseCode != BillingResponseCode.OK) {
            return Purchase.PurchasesResult(result, null)
        }

        return billingClient?.queryPurchases(skuType) ?: Purchase.PurchasesResult(result, null)
    }

    /**
     * Base query products function to back up the public ones.
     *
     * @param[skuType] INAPP or SUBS to retrieve.
     * @param[skuList] list of SKUs to retrieve.
     * @return SkuDetailsResult(BillingResult, List<SkuDetails>?)
     */
    private suspend fun query(
            skuType: String, skuList: List<String>
    ): SkuDetailsResult {
        if (skuList.isEmpty()) {
            return SkuDetailsResult(
                    BillingResult.newBuilder()
                            .setResponseCode(BillingResponseCode.DEVELOPER_ERROR)
                            .build(), null
            )
        }

        return withContext(dispatcher) {
            val result = connect()
            if (result.responseCode != BillingResponseCode.OK) {
                return@withContext SkuDetailsResult(result, null)
            }

            val params = SkuDetailsParams.newBuilder().setSkusList(skuList).setType(skuType).build()
            return@withContext suspendCoroutine { it: Continuation<SkuDetailsResult> ->
                billingClient?.querySkuDetailsAsync(params) { billingResult, resultList ->
                    it.resume(SkuDetailsResult(billingResult, resultList))
                }
            }
        }
    }

    /**
     * Connect to the Play Store
     *
     * @return BillingResult
     */
    private suspend fun connect(): BillingResult {
        if (billingClient == null || billingClient?.isReady == false) {
            withContext(dispatcher) {
                val client =
                        BillingClient.newBuilder(activity.application)
                                .enablePendingPurchases()
                                .setListener { result, purchases ->
                                    if (!purchasesUpdated.isClosedForSend) {
                                        purchasesUpdated.offer(
                                                Purchase.PurchasesResult(result, purchases)
                                        )
                                    }
                                }
                                .build()
                billingClient = client
                return@withContext suspendCoroutine { it: Continuation<BillingResult> ->
                    client.startConnection(object : BillingClientStateListener {
                        override fun onBillingSetupFinished(result: BillingResult) {
                            if (result.responseCode != BillingResponseCode.OK) {
                                // Connection failed
                                billingClient = null
                            }

                            it.resume(result)
                        }

                        override fun onBillingServiceDisconnected() {
                            // We'll build up a new connection upon next request.
                            billingClient = null
                        }
                    })
                }
            }
        }

        // Nothing to do, return ok
        return BillingResult.newBuilder().setResponseCode(BillingResponseCode.OK).build()
    }

    /**
     * Closes the BillingClient and any open BroadcastChannels. Call this when you're done or
     * your Activity is about to be destroyed.
     */
    fun close() {
        purchasesUpdated.close()

        billingClient?.endConnection()
        billingClient = null
    }
}