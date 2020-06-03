---
title: Inventory.<init> - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [Inventory](index.html) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`Inventory(details: SkuDetails)``Inventory(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, price: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, priceAmountMicros: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, priceCurrencyCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, skuDetails: SkuDetails? = null)`

Data class for handling items to be purchased.

### Parameters

`sku` - product sku

`type` - type of product. Will be SkuType.INAPP or SkuType.SUBS

`price` - formatted price of item with currency symbol. (localized)

`priceAmountMicros` - price in micro units (1,000,000 = 1 currency unit) (localized)

`priceCurrencyCode` - ISO 4217 currency code (localized)

`title` - product name

`description` - product description

`skuDetails` - SkuDetails object it was created from, if any