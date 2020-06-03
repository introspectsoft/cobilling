[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [checkPurchases](./check-purchases.md)

# checkPurchases

`@CheckReturnValue fun checkPurchases(skuType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Get purchases information from play store and triggers callback like it's coming from
onPurchasesUpdated().

Should be triggered when an activity loads to handle new purchases.

### Parameters

`skuType` - sku type to check for (INAPP or SUBS)