[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [purchase](./purchase.md)

# purchase

`@CheckReturnValue fun purchase(inventory: `[`Inventory`](../-inventory/index.md)`): Single<`[`PurchaseResponse`](../-purchase-response/index.md)`>`

Purchases the given inventory. which can be an InApp purchase or a subscription.
You can get an instance of Inventory through the [queryInAppPurchases](query-in-app-purchases.md) or
[querySubscriptions](query-subscriptions.md) method. Make sure that the billing for the type is supported by
using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md) for subscriptions.
In case of an error a [PurchaseException](../-purchase-exception/index.md) will be emitted.

### Parameters

`inventory` - the given inventory to purchase. Can either be an InApp purchase or a subscription.