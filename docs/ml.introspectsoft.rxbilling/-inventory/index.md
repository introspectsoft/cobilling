---
title: Inventory - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [Inventory](./index.html)

# Inventory

`data class Inventory : `[`DataUtils`](../-data-utils/index.html)

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

### Constructors

| [&lt;init&gt;](-init-.html) | `Inventory(details: SkuDetails)`<br>Data class for handling items to be purchased.`Inventory(sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, price: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, priceAmountMicros: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, priceCurrencyCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, skuDetails: SkuDetails? = null)` |

### Properties

| [description](description.html) | product description`val description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [price](price.html) | formatted price of item with currency symbol. (localized)`val price: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [priceAmountMicros](price-amount-micros.html) | price in micro units (1,000,000 = 1 currency unit) (localized)`val priceAmountMicros: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [priceAsBigDecimal](price-as-big-decimal.html) | `val priceAsBigDecimal: `[`BigDecimal`](https://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html) |
| [priceCurrency](price-currency.html) | `val priceCurrency: `[`Currency`](https://docs.oracle.com/javase/6/docs/api/java/util/Currency.html) |
| [priceCurrencyCode](price-currency-code.html) | ISO 4217 currency code (localized)`val priceCurrencyCode: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [sku](sku.html) | product sku`val sku: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [skuDetails](sku-details.html) | SkuDetails object it was created from, if any`val skuDetails: SkuDetails?` |
| [title](title.html) | product name`val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [type](type.html) | type of product. Will be SkuType.INAPP or SkuType.SUBS`val type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

