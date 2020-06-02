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

package ml.introspectsoft.rxbilling.base

import androidx.annotation.IntDef
import io.reactivex.rxjava3.annotations.CheckReturnValue
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Billing interface for Google's In-app Billing.
 * https://developer.android.com/google/play/billing/billing_reference.html
 */
interface RxBilling {
    /**
     * Queries inapp purchases by the given sku ids and emits those one by one and then completes.
     * Make sure that the billing is supported first by using [.isBillingForInAppSupported].
     *
     * @param skuIds the sku ids to query. It should contain at least one id.
     * @return Observable emitting the available queried inapp purchases.
     */
    @CheckReturnValue
    fun queryInAppPurchases(vararg skuIds: String): Observable<ml.introspectsoft.rxbilling.base.Inventory?>

    /**
     * Queries subscriptions by the given sku ids and emits those one by one and then completes.
     * Make sure that the billing is supported first by using [.isBillingForSubscriptionsSupported].
     *
     * @param skuIds the sku ids to query. It should contain at least one id.
     * @return Observable emitting the available queried subscriptions.
     */
    @CheckReturnValue
    fun querySubscriptions(vararg skuIds: String): Observable<ml.introspectsoft.rxbilling.base.Inventory?>

    /**
     * Checks whether billing for inapp is supported or not.
     * In case it is the Completable will just complete.
     * Otherwise a [NoBillingSupportedException] will be thrown.
     *
     * @return Completable which will complete in case it is supported. Otherwise an error will be emitted.
     */
    @get:CheckReturnValue
    val isBillingForInAppSupported: Completable

    /**
     * Checks whether billing for subscriptions is supported or not.
     * In case it is the Completable will just complete.
     * Otherwise a [NoBillingSupportedException] will be thrown.
     *
     * @return Completable which will complete in case it is supported. Otherwise an error will be emitted.
     */
    @get:CheckReturnValue
    val isBillingForSubscriptionsSupported: Completable

    /**
     * Purchases the given inventory. which can be an inapp purchase or a subscription.
     * You can get an instance of Inventory through the [.queryInAppPurchases] or
     * [.querySubscriptions] method. Make sure that the billing for the type is supported by
     * using [.isBillingForInAppSupported] or [.isBillingForSubscriptionsSupported].
     * In case of an error a [PurchaseException] will be emitted.
     *
     * @param inventory the given inventory to purcahse. Can either be an inapp purcahse or a subscription.
     * @param developerPayload custom developer payload that will be sent with
     */
    @CheckReturnValue
    fun purchase(
        inventory: ml.introspectsoft.rxbilling.base.Inventory,
        developerPayload: String
    ): Single<PurchaseResponse>

    /**
     * @return all of the inapp purchases that have taken place already on by one and then completes.
     * In case there were none the Observable will just complete.
     */
    @get:CheckReturnValue
    val purchasedInApps: Observable<Purchased>

    /**
     * @return all of the subscription purchases that have taken place already on by one and then completes.
     * In case there were none the Observable will just complete.
     */
    @get:CheckReturnValue
    val purchasedSubscriptions: Observable<Purchased>

    /**
     * Consumes the given inapp purchase which has been bought.
     *
     * @param purchasedInApp the purchased in app purchase to consume
     * @return Single containing the BillingResponse
     */
    @CheckReturnValue
    fun consumePurchase(purchasedInApp: Purchased): Single<Int>

    /**
     * Destroys the current session and releases all of the references.
     * Call this when you're done or your Activity is about to be destroyed.
     */
    fun destroy()

    /** Possible response codes.  */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef(
        BillingResponse.OK,
        BillingResponse.USER_CANCELED,
        BillingResponse.SERVICE_UNAVAILABLE,
        BillingResponse.BILLING_UNAVAILABLE,
        BillingResponse.ITEM_UNAVAILABLE,
        BillingResponse.DEVELOPER_ERROR,
        BillingResponse.ERROR,
        BillingResponse.ITEM_ALREADY_OWNED,
        BillingResponse.ITEM_NOT_OWNED
    )
    annotation class BillingResponse {
        companion object {
            /** Success  */
            const val OK = 0

            /** User pressed back or canceled a dialog  */
            const val USER_CANCELED = 1

            /** Network connection is down  */
            const val SERVICE_UNAVAILABLE = 2

            /** Billing API version is not supported for the type requested  */
            const val BILLING_UNAVAILABLE = 3

            /** Requested product is not available for purchase  */
            const val ITEM_UNAVAILABLE = 4

            /**
             * Invalid arguments provided to the API. This error can also indicate that the application was
             * not correctly signed or properly set up for In-app Billing in Google Play, or does not have
             * the necessary permissions in its manifest
             */
            const val DEVELOPER_ERROR = 5

            /** Fatal error during the API action  */
            const val ERROR = 6

            /** Failure to purchase since item is already owned  */
            const val ITEM_ALREADY_OWNED = 7

            /** Failure to consume since item is not owned  */
            const val ITEM_NOT_OWNED = 8
        }
    }
}