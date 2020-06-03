---
title: PurchasesUpdate - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [PurchasesUpdate](./index.html)

# PurchasesUpdate

`data class PurchasesUpdate : `[`DataUtils`](../-data-utils/index.html)

Updated purchase data from BillingClient.onPurchasesUpdated

### Parameters

`result` - BillingResult of response

`purchases` - list of changed purchases to process

### Constructors

| [&lt;init&gt;](-init-.html) | `PurchasesUpdate(purchasesResult: PurchasesResult)`<br>Updated purchase data from BillingClient.onPurchasesUpdated`PurchasesUpdate(result: BillingResult, purchases: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Purchase>?)` |

### Properties

| [purchases](purchases.html) | list of changed purchases to process`val purchases: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Purchase>?` |
| [result](result.html) | BillingResult of response`val result: BillingResult` |

