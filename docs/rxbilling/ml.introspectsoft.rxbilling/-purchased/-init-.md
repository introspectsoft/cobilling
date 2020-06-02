[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [Purchased](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Purchased(packageName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, productId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseToken: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, purchaseState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, purchaseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0)`

Data class for handling purchase history data

### Parameters

`packageName` - package that originated the purchase

`productId` - product sku

`purchaseToken` - unique identifier for the purchase.

`purchaseState` - purchase state of the order. Should verify it is [PurchaseState.PURCHASED](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased)
    and not [PurchaseState.PENDING](https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending)

`purchaseTime` - purchase time in milliseconds