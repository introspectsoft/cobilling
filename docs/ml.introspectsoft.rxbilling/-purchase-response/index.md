---
title: PurchaseResponse - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [PurchaseResponse](./index.html)

# PurchaseResponse

`data class PurchaseResponse : `[`DataUtils`](../-data-utils/index.html)

Data class for handling purchase history data

### Parameters

`packageName` - package that originated the purchase

`productId` - product sku

`purchaseToken` - unique identifier for the purchase.

`purchaseState` - purchase state of the order. Should verify it is [PurchaseState.PURCHASED](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased)
    and not [PurchaseState.PENDING](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending)

`purchaseTime` - purchase time in milliseconds

`purchaseOrderId` - order id of the purchase

`isAcknowledged` - is the purchase acknowledged

`isAutoRenewing` - is the purchase auto renewing

### Constructors

| [&lt;init&gt;](-init-.html) | Create a new PurchaseResponse`PurchaseResponse(purchase: Purchase)`<br>`PurchaseResponse(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, productId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, purchaseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0, purchaseOrderId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, isAcknowledged: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, isAutoRenewing: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false)` |

### Properties

| [isAcknowledged](is-acknowledged.html) | is the purchase acknowledged`val isAcknowledged: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isAutoRenewing](is-auto-renewing.html) | is the purchase auto renewing`val isAutoRenewing: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [packageName](package-name.html) | package that originated the purchase`val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [productId](product-id.html) | product sku`val productId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [purchaseOrderId](purchase-order-id.html) | order id of the purchase`val purchaseOrderId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [purchaseState](purchase-state.html) | purchase state of the order. Should verify it is [PurchaseState.PURCHASED](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased)     and not [PurchaseState.PENDING](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending)`val purchaseState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [purchaseTime](purchase-time.html) | purchase time in milliseconds`val purchaseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [purchaseToken](purchase-token.html) | unique identifier for the purchase.`val purchaseToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

