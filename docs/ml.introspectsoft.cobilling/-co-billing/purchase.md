---
title: CoBilling.purchase - cobilling
---

[cobilling](../../index.html) / [ml.introspectsoft.cobilling](../index.html) / [CoBilling](index.html) / [purchase](./purchase.html)

# purchase

`suspend fun purchase(product: SkuDetails, accountId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, profileId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): BillingResult`

Purchases the given item. You can get an instance of SkuDetails through the [queryInAppPurchases](query-in-app-purchases.html) or
[querySubscriptions](query-subscriptions.html) method.

The values of [accountId](purchase.html#ml.introspectsoft.cobilling.CoBilling$purchase(com.android.billingclient.api.SkuDetails, kotlin.String, kotlin.String)/accountId) and [profileId](purchase.html#ml.introspectsoft.cobilling.CoBilling$purchase(com.android.billingclient.api.SkuDetails, kotlin.String, kotlin.String)/profileId) will be hashed to remove personally identifying
information. Values are hashed using [String.toSha256](../../ml.introspectsoft.cobilling.extensions/kotlin.-string/to-sha256.html) which is included in this library.

### Parameters

`product` - the product to purchase

`accountId` - account id to be sent with the purchase

`profileId` - profile id to be sent with the purchase if your app supports multiple profiles