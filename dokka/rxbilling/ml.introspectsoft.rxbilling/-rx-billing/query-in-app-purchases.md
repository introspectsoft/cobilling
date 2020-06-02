[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [queryInAppPurchases](./query-in-app-purchases.md)

# queryInAppPurchases

`@CheckReturnValue fun queryInAppPurchases(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.md)`?>`

Queries InApp purchases by the given sku ids and emits those one by one and then completes.

### Parameters

`skuIds` - the sku ids to query. It should contain at least one id.

**Return**
Observable emitting the available queried InApp purchases.

