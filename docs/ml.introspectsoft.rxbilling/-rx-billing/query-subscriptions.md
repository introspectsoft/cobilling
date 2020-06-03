---
title: RxBilling.querySubscriptions - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [RxBilling](index.html) / [querySubscriptions](./query-subscriptions.html)

# querySubscriptions

`@CheckReturnValue fun querySubscriptions(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Observable<`[`Inventory`](../-inventory/index.html)`?>`

Queries subscriptions by the given sku ids and emits those one by one and then completes.
Make sure that the billing is supported first by using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.html).

### Parameters

`skuIds` - the sku ids to query. It should contain at least one id.

**Return**
Observable emitting the available queried subscriptions.

