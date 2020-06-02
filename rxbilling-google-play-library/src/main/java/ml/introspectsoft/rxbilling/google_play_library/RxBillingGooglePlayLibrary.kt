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

package ml.introspectsoft.rxbilling.google_play_library

import android.app.Activity
import com.android.billingclient.api.*
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ml.introspectsoft.rxbilling.base.*

class RxBillingGooglePlayLibrary(
    private val activity: Activity,
    private val logger: Logger = TimberLogger(),
    private val scheduler: Scheduler = Schedulers.io()
) : RxBilling {
    private var billingClient: BillingClient? = null

    private val purchaseSubject = PublishSubject.create<PurchasesUpdate>()

    override fun destroy() {
        billingClient?.endConnection()
        billingClient = null
    }

    @CheckReturnValue
    override fun queryInAppPurchases(vararg skuIds: String?) = query(
        BillingClient.SkuType.INAPP,
        skuIds.toList().filterNotNull(),
        PlayBillingInventoryInApp::create
    )

    @CheckReturnValue
    override fun querySubscriptions(vararg skuIds: String?) = query(
        BillingClient.SkuType.SUBS,
        skuIds.toList().filterNotNull(),
        PlayBillingInventorySubscription::create
    )

    @CheckReturnValue
    private fun <T> query(
        skuType: String,
        skuList: List<String>,
        converter: (SkuDetails) -> T
    ): Observable<T> {
        if (skuList.isEmpty()) {
            throw IllegalArgumentException("No ids were passed")
        }

        return connect().flatMapObservable { client ->
            Observable.create<T> { emitter ->
                val skuDetailsParams = SkuDetailsParams.newBuilder()
                    .setSkusList(skuList).setType(skuType)
                    .build()

                client.querySkuDetailsAsync(skuDetailsParams) { responseCode, skuDetailsList: List<SkuDetails>? ->
                    if ((responseCode ?: -1) == RxBilling.BillingResponse.OK) {
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

    @get:CheckReturnValue
    override val isBillingForInAppSupported
        get() =
            Completable.complete()
                .subscribeOn(scheduler) // https://issuetracker.google.com/issues/123447114

    @get:CheckReturnValue
    override val isBillingForSubscriptionsSupported
        get() =
            Completable.complete()
                .subscribeOn(scheduler) // https://issuetracker.google.com/issues/123447114

    @CheckReturnValue
    override fun purchase(
        inventory: Inventory,
        developerPayload: String
    ): Single<PurchaseResponse> {
        logger.d("Trying to purchase $inventory")

        return connect()
            .flatMap { client ->
                Single.create<PurchaseResponse> { emitter ->
                    val params = BillingFlowParams.newBuilder()
                        .setSkuDetails(inventory.skuDetails)
                        .build()

                    val responseCode = client.launchBillingFlow(activity, params)

                    logger.d("ResponseCode $responseCode for purchase when launching billing flow with $inventory")

                    emitter.setDisposable(purchaseSubject
                        .takeUntil { (_, purchases) -> purchases?.any { it.sku == inventory.sku } == true }
                        .firstOrError()
                        .subscribe({ (code, purchases) ->
                            when (code.responseCode) {
                                RxBilling.BillingResponse.OK -> {
                                    val match =
                                        requireNotNull(purchases).first { it.sku == inventory.sku }
                                    emitter.onSuccess(
                                        PurchaseResponse(
                                            match.packageName,
                                            match.sku,
                                            match.purchaseToken,
                                            DEFAULT_PURCHASE_STATE,
                                            match.purchaseTime
                                        )
                                    )
                                }
                                else -> emitter.onError(PurchaseException(code.responseCode))
                            }
                        }, emitter::onError)
                    )
                }
            }.subscribeOn(scheduler)
    }

    @CheckReturnValue
    override fun consumePurchase(purchasedInApp: Purchased): Single<Int> {
        logger.d("Trying to consume purchase $purchasedInApp")

        return connect()
            .flatMap { client ->
                Single.create<Int> { emitter ->
                    client.consumeAsync(purchasedInApp.purchaseToken) { responseCode, _ ->
                        emitter.onSuccess(responseCode)
                    }
                }
            }
            .subscribeOn(scheduler)
    }

    @get:CheckReturnValue
    override val purchasedInApps: Observable<Purchased>
        get() = getPurchased(BillingClient.SkuType.INAPP) {
            Purchased(
                it.packageName,
                it.sku,
                it.purchaseToken,
                DEFAULT_PURCHASE_STATE,
                it.purchaseTime
            )
        }

    @get:CheckReturnValue
    override val purchasedSubscriptions: Observable<Purchased>
        get() = getPurchased(BillingClient.SkuType.SUBS) {
            Purchased(
                it.packageName,
                it.sku,
                it.purchaseToken,
                DEFAULT_PURCHASE_STATE,
                it.purchaseTime
            )
        }

    @CheckReturnValue
    fun <T> getPurchased(skuType: String, converter: (Purchase) -> T) = connect()
        .flatMapObservable { client ->
            Observable.create<T> { emitter ->
                client.queryPurchaseHistoryAsync(skuType) { responseCode, purchasesList: List<Purchase>? ->
                    if (responseCode == RxBilling.BillingResponse.OK) {
                        if (purchasesList != null && purchasesList.isNotEmpty()) {
                            for (purchase in purchasesList) {
                                emitter.onNext(converter.invoke(purchase))
                            }
                        }

                        emitter.onComplete()
                    } else {
                        emitter.onError(InAppBillingException(responseCode))
                    }
                }
            }
        }.subscribeOn(scheduler)

    @CheckReturnValue
    private fun connect() = Single.create<BillingClient> { emitter ->
        if (billingClient == null || billingClient?.isReady == false) {
            val client = BillingClient.newBuilder(activity.application)
                .setListener { responseCode, purchases ->
                    purchaseSubject.onNext(
                        PurchasesUpdate(
                            responseCode,
                            purchases
                        )
                    )
                }
                .build()

            billingClient = client

            client.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(@RxBilling.BillingResponse billingResponseCode: Int) {
                    if (billingResponseCode == RxBilling.BillingResponse.OK) {
                        logger.d("Connected to BillingClient")
                        emitter.onSuccess(client)
                    } else {
                        logger.d("Could not connect to BillingClient. ResponseCode: $billingResponseCode")
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
        val responseCode: BillingResult,
        val purchases: List<Purchase>?
    )

    internal companion object {
        // https://issuetracker.google.com/issues/123449154
        const val DEFAULT_PURCHASE_STATE = 0
    }
}
