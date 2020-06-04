---
title: CoBilling - cobilling
---

[cobilling](../../index.html) / [ml.introspectsoft.cobilling](../index.html) / [CoBilling](./index.html)

# CoBilling

`@ExperimentalCoroutinesApi class CoBilling`

Kotlin coroutine wrappers for the Google Play Billing library

* Google Play Billing v2.2.1
* coroutines v2.3.0-alpha03

### Parameters

`activity` - the activity we're running on

`useDispatcher` - an optional context to run on (Default: Dispatchers.IO)

### Constructors

| [&lt;init&gt;](-init-.html) | Kotlin coroutine wrappers for the Google Play Billing library`CoBilling(activity: `[`Activity`](https://developer.android.com/reference/android/app/Activity.html)`, useDispatcher: CoroutineDispatcher? = null)` |

### Properties

| [purchasesUpdated](purchases-updated.html) | `val purchasesUpdated: BroadcastChannel<PurchasesResult>` |

### Functions

| [acknowledgePurchase](acknowledge-purchase.html) | Acknowledge the purchase Purchases not acknowledged or consumed after 3 days are refunded.`suspend fun acknowledgePurchase(purchased: Purchase): BillingResult` |
| [checkPurchases](check-purchases.html) | Get purchases information from play store and triggers callback like it's coming from onPurchasesUpdated().`suspend fun checkPurchases(skuType: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](close.html) | Closes the BillingClient and any open BroadcastChannels. Call this when you're done or your Activity is about to be destroyed.`fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [consumePurchase](consume-purchase.html) | Consumes the in app purchase. Purchases not acknowledged or consumed after 3 days are refunded.`suspend fun consumePurchase(purchased: Purchase): ConsumeResult` |
| [getPurchasedInApps](get-purchased-in-apps.html) | Get all of the InApp purchases that have taken place already`suspend fun getPurchasedInApps(): PurchasesResult` |
| [getPurchasedSubscriptions](get-purchased-subscriptions.html) | Get all of the subscription purchases that have taken place already`suspend fun getPurchasedSubscriptions(): PurchasesResult` |
| [purchase](purchase.html) | Purchases the given item. You can get an instance of SkuDetails through the [queryInAppPurchases](query-in-app-purchases.html) or [querySubscriptions](query-subscriptions.html) method.`suspend fun purchase(product: SkuDetails, accountId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, profileId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null): BillingResult` |
| [queryInAppPurchases](query-in-app-purchases.html) | Queries for a list of in app products by SKU`suspend fun queryInAppPurchases(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): SkuDetailsResult` |
| [querySubscriptions](query-subscriptions.html) | Queries for a list of subscription products by SKU`suspend fun querySubscriptions(vararg skuIds: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): SkuDetailsResult` |

### Companion Object Properties

| [BUFFER_SIZE](-b-u-f-f-e-r_-s-i-z-e.html) | `const val BUFFER_SIZE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

