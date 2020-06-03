[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [purchase](./purchase.md)

# purchase

`@CheckReturnValue fun purchase(inventory: `[`Inventory`](../-inventory/index.md)`, accountId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, profileId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): Single<BillingResult>`

Purchases the given inventory. which can be an InApp purchase or a subscription.
You can get an instance of Inventory through the [queryInAppPurchases](query-in-app-purchases.md) or
[querySubscriptions](query-subscriptions.md) method. Make sure that the billing for the type is supported by
using [isBillingForSubscriptionsSupported](is-billing-for-subscriptions-supported.md) for subscriptions.
In case of an error a [PurchaseException](../-purchase-exception/index.md) will be emitted.

The values of [accountId](purchase.md#ml.introspectsoft.rxbilling.RxBilling$purchase(ml.introspectsoft.rxbilling.Inventory, kotlin.String, kotlin.String)/accountId) and [profileId](purchase.md#ml.introspectsoft.rxbilling.RxBilling$purchase(ml.introspectsoft.rxbilling.Inventory, kotlin.String, kotlin.String)/profileId) will be hashed to remove personally identifying
information. Values are hashed using [String.toSha256](../../ml.introspectsoft.rxbilling.extensions/kotlin.-string/to-sha256.md) which is included in this library.

### Parameters

`inventory` - the given inventory to purchase. Can either be a one time purchase or a subscription.

`accountId` - account id to be sent with the purchase

`profileId` - profile id to be sent with the purchase if your app supports multiple profiles