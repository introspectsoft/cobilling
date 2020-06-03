---
title: RxBilling.queryInAppPurchases - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [RxBilling](index.html) / [queryInAppPurchases](./query-in-app-purchases.html)

# queryInAppPurchases

`@CheckReturnValue fun queryInAppPurchases(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.html)`?>`

Queries InApp purchases by the given sku ids and emits those one by one and then completes.

### Parameters

`skuIds` - the sku ids to query. It should contain at least one id.

**Return**
Observable emitting the available queried InApp purchases.

