---
title: RxBilling - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [RxBilling](./index.html)

# RxBilling

`class RxBilling`

Billing interface for Google's In-app Billing

Currently supports 2.2.1

### Constructors

| [&lt;init&gt;](-init-.html) | Billing interface for Google's In-app Billing`RxBilling(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, logger: `[`Logger`](../-logger/index.html)` = LogcatLogger(), scheduler: Scheduler = Schedulers.io())` |

### Properties

| [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.html) | Checks whether billing for subscriptions is supported or not. In case it is the Completable will just complete. Otherwise a [NoBillingSupportedException](../-no-billing-supported-exception/index.html) will be thrown.`val isBillingForSubscriptionsSupported: Completable` |
| [purchasedInApps](purchased-in-apps.html) | All of the InApp purchases that have taken place already on by one and then completes. In case there were none the Observable will just complete.`val purchasedInApps: Observable<`[`PurchaseResponse`](../-purchase-response/index.html)`>` |
| [purchasedSubscriptions](purchased-subscriptions.html) | All of the subscription purchases that have taken place already on by one and then completes. In case there were none the Observable will just complete.`val purchasedSubscriptions: Observable<`[`PurchaseResponse`](../-purchase-response/index.html)`>` |
| [purchasesUpdated](purchases-updated.html) | `val purchasesUpdated: PublishSubject<`[`PurchasesUpdate`](../-purchases-update/index.html)`>` |

### Functions

| [acknowledgePurchase](acknowledge-purchase.html) | Acknowledge the given InApp purchase which has been bought. Purchases not acknowledged or consumed after 3 days are refunded.`fun acknowledgePurchase(purchased: Purchase): Single<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [checkPurchases](check-purchases.html) | Get purchases information from play store and triggers callback like it's coming from onPurchasesUpdated().`fun checkPurchases(skuType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [consumePurchase](consume-purchase.html) | Consumes the given InApp purchase which has been bought. Purchases not acknowledged or consumed after 3 days are refunded.`fun consumePurchase(purchased: Purchase): Single<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [destroy](destroy.html) | Destroys the current session and releases all of the references. Call this when you're done or your Activity is about to be destroyed.`fun destroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [purchase](purchase.html) | Purchases the given inventory. which can be an InApp purchase or a subscription. You can get an instance of Inventory through the [queryInAppPurchases](query-in-app-purchases.html) or [querySubscriptions](query-subscriptions.html) method. Make sure that the billing for the type is supported by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.html) for subscriptions. In case of an error a [PurchaseException](../-purchase-exception/index.html) will be emitted.`fun purchase(inventory: `[`Inventory`](../-inventory/index.html)`, accountId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, profileId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): Single<BillingResult>` |
| [queryInAppPurchases](query-in-app-purchases.html) | Queries InApp purchases by the given sku ids and emits those one by one and then completes.`fun queryInAppPurchases(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.html)`?>` |
| [querySubscriptions](query-subscriptions.html) | Queries subscriptions by the given sku ids and emits those one by one and then completes. Make sure that the billing is supported first by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.html).`fun querySubscriptions(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.html)`?>` |

