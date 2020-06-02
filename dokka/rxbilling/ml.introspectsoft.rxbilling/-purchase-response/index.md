[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [PurchaseResponse](./index.md)

# PurchaseResponse

`data class PurchaseResponse`

Data class for handling purchase response events.

### Parameters

`packageName` - package that originated the purchase

`productId` - product sku

`purchaseToken` - unique identifier for the purchase.

`purchaseState` - purchase state of the order. Should verify it is [PurchaseState.PURCHASED](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased)
    and not [PurchaseState.PENDING](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending)

`purchaseTime` - purchase time in milliseconds

### Constructors

| [&lt;init&gt;](-init-.md) | Data class for handling purchase response events.`PurchaseResponse(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, productId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, purchaseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`)` |

### Properties

| [packageName](package-name.md) | package that originated the purchase`val packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [productId](product-id.md) | product sku`val productId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [purchaseState](purchase-state.md) | purchase state of the order. Should verify it is [PurchaseState.PURCHASED](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased)     and not [PurchaseState.PENDING](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending)`val purchaseState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [purchaseTime](purchase-time.md) | purchase time in milliseconds`val purchaseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [purchaseToken](purchase-token.md) | unique identifier for the purchase.`val purchaseToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

