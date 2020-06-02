[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [querySubscriptions](./query-subscriptions.md)

# querySubscriptions

`@CheckReturnValue fun querySubscriptions(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.md)`?>`

Queries subscriptions by the given sku ids and emits those one by one and then completes.
Make sure that the billing is supported first by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md).

### Parameters

`skuIds` - the sku ids to query. It should contain at least one id.

**Return**
Observable emitting the available queried subscriptions.

