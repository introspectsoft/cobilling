[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [PurchasesUpdate](./index.md)

# PurchasesUpdate

`data class PurchasesUpdate : `[`DataUtils`](../-data-utils/index.md)

Updated purchase data from BillingClient.onPurchasesUpdated

### Parameters

`result` - BillingResult of response

`purchases` - list of changed purchases to process

### Constructors

| [&lt;init&gt;](-init-.md) | `PurchasesUpdate(purchasesResult: PurchasesResult)`<br>Updated purchase data from BillingClient.onPurchasesUpdated`PurchasesUpdate(result: BillingResult, purchases: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Purchase>?)` |

### Properties

| [purchases](purchases.md) | list of changed purchases to process`val purchases: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Purchase>?` |
| [result](result.md) | BillingResult of response`val result: BillingResult` |

