/*
 * Copyright (c) 2018 Vanniktech - Niklas Baudy
 * Modifications Copyright (c) 2020. Jason Burgess
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

package ml.introspectsoft.rxbilling

import android.app.Activity
import com.android.billingclient.api.*
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ml.introspectsoft.rxbilling.SkuDetails.toInventory

/**
 * Billing interface for Google's In-app Billing
 *
 * Currently supports 2.2.1
 */
class RxBilling(
        private val activity: Activity,
        private val logger: Logger = TimberLogger(),
        private val scheduler: Scheduler = Schedulers.io()
) {
    private var billingClient: BillingClient? = null
    private val purchaseSubject = PublishSubject.create<PurchasesUpdate>()

    /**
     * Destroys the current session and releases all of the references.
     * Call this when you're done or your Activity is about to be destroyed.
     */
    fun destroy() {
        billingClient?.endConnection()
        billingClient = null
    }

    /**
     * Queries InApp purchases by the given sku ids and emits those one by one and then completes.
     *
     * @param skuIds the sku ids to query. It should contain at least one id.
     * @return Observable emitting the available queried InApp purchases.
     */
    @CheckReturnValue
    fun queryInAppPurchases(vararg skuIds: String): Observable<Inventory?> = query(
            BillingClient.SkuType.INAPP, skuIds.toList(), SkuDetails::toInventory
    )

    /**
     * Queries subscriptions by the given sku ids and emits those one by one and then completes.
     * Make sure that the billing is supported first by using [isBillingForSubscriptionsSupported].
     *
     * @param skuIds the sku ids to query. It should contain at least one id.
     * @return Observable emitting the available queried subscriptions.
     */
    @CheckReturnValue
    fun querySubscriptions(vararg skuIds: String): Observable<Inventory?> = query(
            BillingClient.SkuType.SUBS, skuIds.toList(), SkuDetails::toInventory
    )

    @CheckReturnValue
    private fun <T> query(
            skuType: String, skuList: List<String>, converter: (SkuDetails) -> T
    ): Observable<T> {
        if (skuList.isEmpty()) {
            throw IllegalArgumentException("No ids were passed")
        }

        return connect().flatMapObservable { client ->
            Observable.create<T> { emitter ->
                val skuDetailsParams =
                        SkuDetailsParams.newBuilder().setSkusList(skuList).setType(skuType).build()

                client.querySkuDetailsAsync(skuDetailsParams) { responseCode, skuDetailsList: List<SkuDetails>? ->
                    if ((responseCode ?: -1) == BillingResponse.OK) {
                        if (skuDetailsList != null) {
                            for (skuDetail in skuDetailsList) {
                                emitter.onNext(converter.invoke(skuDetail))
                            }
                        }

                        emitter.onComplete()
                    } else {
                        emitter.onError(RuntimeException("Querying failed with responseCode: $responseCode"))
                    }
                }
            }
        }.subscribeOn(scheduler)
    }

    /**
     * Checks whether billing for subscriptions is supported or not.
     * In case it is the Completable will just complete.
     * Otherwise a [NoBillingSupportedException] will be thrown.
     *
     * @return Completable which will complete in case it is supported. Otherwise an error will be emitted.
     */
    @get:CheckReturnValue val isBillingForSubscriptionsSupported: @NonNull Completable
        // TODO: Implement the actual isFeatureSupported call
        get() = Completable.complete()
                .subscribeOn(scheduler) // https://issuetracker.google.com/issues/123447114

    /**
     * Purchases the given inventory. which can be an InApp purchase or a subscription.
     * You can get an instance of Inventory through the [queryInAppPurchases] or
     * [querySubscriptions] method. Make sure that the billing for the type is supported by
     * using [isBillingForSubscriptionsSupported] for subscriptions.
     * In case of an error a [PurchaseException] will be emitted.
     *
     * @param inventory the given inventory to purchase. Can either be an InApp purchase or a subscription.
     */
    @CheckReturnValue
    fun purchase(inventory: Inventory): Single<PurchaseResponse> {
        logger.d("Trying to purchase $inventory")

        return connect().flatMap { client ->
            Single.create<PurchaseResponse> { emitter ->
                val params =
                        BillingFlowParams.newBuilder().setSkuDetails(inventory.skuDetails).build()

                val responseCode = client.launchBillingFlow(activity, params)

                logger.d("ResponseCode $responseCode for purchase when launching billing flow with $inventory")

                emitter.setDisposable(purchaseSubject.takeUntil { (_, purchases) -> purchases?.any { it.sku == inventory.sku } == true }
                                              .firstOrError()
                                              .subscribe({ (code, purchases) ->
                                                             when (code.responseCode) {
                                                                 BillingResponse.OK -> {
                                                                     val match = requireNotNull(
                                                                             purchases
                                                                     ).first { it.sku == inventory.sku }
                                                                     emitter.onSuccess(
                                                                             PurchaseResponse(
                                                                                     match.packageName,
                                                                                     match.sku,
                                                                                     match.purchaseToken,
                                                                                     match.purchaseState,
                                                                                     match.purchaseTime
                                                                             )
                                                                     )
                                                                 }
                                                                 else               -> emitter.onError(
                                                                         PurchaseException(
                                                                                 code.responseCode
                                                                         )
                                                                 )
                                                             }
                                                         }, emitter::onError)
                )
            }
        }.subscribeOn(scheduler)
    }

    /**
     * Acknowledge the given InApp purchase which has been bought.
     * Purchases not acknowledged or consumed after 3 days are refunded.
     *
     * @param purchasedInApp the purchased in app purchase to consume
     * @return Single containing the BillingResponse
     */
    @CheckReturnValue
    fun acknowledgePurchase(purchasedInApp: Purchased): Single<Int> {
        logger.d("Trying to acknowledge purchase $purchasedInApp")

        return connect().flatMap { client ->
            Single.create<Int> { emitter ->
                val params =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchasedInApp.purchaseToken)
                                .build()
                client.acknowledgePurchase(params) { result ->
                    emitter.onSuccess(
                            result?.responseCode ?: BillingClient.BillingResponseCode.ERROR
                    )
                }
            }
        }.subscribeOn(scheduler)

    }

    /**
     * Consumes the given InApp purchase which has been bought.
     * Purchases not acknowledged or consumed after 3 days are refunded.
     *
     * @param purchasedInApp the purchased in app purchase to consume
     * @return Single containing the BillingResponse
     */
    @CheckReturnValue
    fun consumePurchase(purchasedInApp: Purchased): Single<Int> {
        logger.d("Trying to consume purchase $purchasedInApp")

        return connect().flatMap { client ->
            Single.create<Int> { emitter ->
                val params =
                        ConsumeParams.newBuilder()
                                .setPurchaseToken(purchasedInApp.purchaseToken)
                                .build()
                client.consumeAsync(params) { result, _ ->
                    emitter.onSuccess(result.responseCode)
                }
            }
        }.subscribeOn(scheduler)
    }

    /**
     * All of the InApp purchases that have taken place already on by one and then completes.
     * In case there were none the Observable will just complete.
     */
    @get:CheckReturnValue val purchasedInApps: Observable<Purchased>
        get() = getPurchased(BillingClient.SkuType.INAPP) {
            Purchased(
                    it.packageName, it.sku, it.purchaseToken, it.purchaseState, it.purchaseTime
            )
        }

    /**
     * @return all of the subscription purchases that have taken place already on by one and then completes.
     * In case there were none the Observable will just complete.
     */
    @get:CheckReturnValue val purchasedSubscriptions: Observable<Purchased>
        get() = getPurchased(BillingClient.SkuType.SUBS) {
            Purchased(
                    it.packageName, it.sku, it.purchaseToken, it.purchaseState, it.purchaseTime
            )
        }

    @CheckReturnValue
    fun <T> getPurchased(skuType: String, converter: (Purchase) -> T): @NonNull Observable<T> =
            connect().flatMapObservable { client ->
                Observable.create<T> { emitter ->
                    // TODO: Check if this works instead of purchaseHistoryAsync
                    val result = client.queryPurchases(skuType)
                    if (result.responseCode == BillingResponse.OK) {
                        result.purchasesList?.forEach {
                            emitter.onNext(converter.invoke(it))
                        }

                        emitter.onComplete()
                    } else {
                        emitter.onError(InAppBillingException(result.responseCode))
                    }
                }
            }.subscribeOn(scheduler)

    @CheckReturnValue
    private fun connect() = Single.create<BillingClient> { emitter ->
        if (billingClient == null || billingClient?.isReady == false) {
            val client =
                    BillingClient.newBuilder(activity.application)
                            .enablePendingPurchases()
                            .setListener { responseCode, purchases ->
                                purchaseSubject.onNext(
                                        PurchasesUpdate(
                                                responseCode, purchases
                                        )
                                )
                            }
                            .build()

            billingClient = client

            client.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(result: BillingResult?) {
                    if (result?.responseCode == BillingResponse.OK) {
                        logger.d("Connected to BillingClient")
                        emitter.onSuccess(client)
                    } else {
                        logger.d("Could not connect to BillingClient. ResponseCode: ${result?.responseCode}")
                        billingClient = null
                    }
                }

                override fun onBillingServiceDisconnected() {
                    billingClient = null // We'll build up a new connection upon next request.
                }

            })
        } else {
            emitter.onSuccess(requireNotNull(billingClient))
        }
    }

    internal data class PurchasesUpdate(
            val responseCode: BillingResult, val purchases: List<Purchase>?
    )
}
