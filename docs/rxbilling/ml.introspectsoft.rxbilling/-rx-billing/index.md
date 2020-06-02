[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](./index.md)

# RxBilling

`class RxBilling`

Billing interface for Google's In-app Billing

Currently supports 2.2.1

### Constructors

| [&lt;init&gt;](-init-.md) | Billing interface for Google's In-app Billing`RxBilling(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, logger: `[`Logger`](../-logger/index.md)` = TimberLogger(), scheduler: Scheduler = Schedulers.io())` |

### Properties

| [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md) | Checks whether billing for subscriptions is supported or not. In case it is the Completable will just complete. Otherwise a [NoBillingSupportedException](../-no-billing-supported-exception/index.md) will be thrown.`val isBillingForSubscriptionsSupported: Completable` |
| [purchasedInApps](purchased-in-apps.md) | All of the InApp purchases that have taken place already on by one and then completes. In case there were none the Observable will just complete.`val purchasedInApps: Observable<`[`Purchased`](../-purchased/index.md)`>` |
| [purchasedSubscriptions](purchased-subscriptions.md) | `val purchasedSubscriptions: Observable<`[`Purchased`](../-purchased/index.md)`>` |

### Functions

| [acknowledgePurchase](acknowledge-purchase.md) | Acknowledge the given InApp purchase which has been bought. Purchases not acknowledged or consumed after 3 days are refunded.`fun acknowledgePurchase(purchasedInApp: `[`Purchased`](../-purchased/index.md)`): Single<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [consumePurchase](consume-purchase.md) | Consumes the given InApp purchase which has been bought. Purchases not acknowledged or consumed after 3 days are refunded.`fun consumePurchase(purchasedInApp: `[`Purchased`](../-purchased/index.md)`): Single<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [destroy](destroy.md) | Destroys the current session and releases all of the references. Call this when you're done or your Activity is about to be destroyed.`fun destroy(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getPurchased](get-purchased.md) | `fun <T> getPurchased(skuType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, converter: (Purchase) -> T): Observable<T>` |
| [purchase](purchase.md) | Purchases the given inventory. which can be an InApp purchase or a subscription. You can get an instance of Inventory through the [queryInAppPurchases](query-in-app-purchases.md) or [querySubscriptions](query-subscriptions.md) method. Make sure that the billing for the type is supported by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md) for subscriptions. In case of an error a [PurchaseException](../-purchase-exception/index.md) will be emitted.`fun purchase(inventory: `[`Inventory`](../-inventory/index.md)`): Single<`[`PurchaseResponse`](../-purchase-response/index.md)`>` |
| [queryInAppPurchases](query-in-app-purchases.md) | Queries InApp purchases by the given sku ids and emits those one by one and then completes.`fun queryInAppPurchases(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.md)`?>` |
| [querySubscriptions](query-subscriptions.md) | Queries subscriptions by the given sku ids and emits those one by one and then completes. Make sure that the billing is supported first by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md).`fun querySubscriptions(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.md)`?>` |

