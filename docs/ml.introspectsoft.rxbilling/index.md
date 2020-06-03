---
title: ml.introspectsoft.rxbilling - rxbilling
---

[rxbilling](../index.html) / [ml.introspectsoft.rxbilling](./index.html)

## Package ml.introspectsoft.rxbilling

### Types

| [DataUtils](-data-utils/index.html) | Extension functions for data classes`interface DataUtils` |
| [Inventory](-inventory/index.html) | Data class for handling items to be purchased.`data class Inventory : `[`DataUtils`](-data-utils/index.html) |
| [LogcatLogger](-logcat-logger/index.html) | Wrapper for Logcat logging.`class LogcatLogger : `[`Logger`](-logger/index.html) |
| [Logger](-logger/index.html) | Wrapper to the user's preferred Log function`interface Logger` |
| [PurchaseResponse](-purchase-response/index.html) | Data class for handling purchase history data`data class PurchaseResponse : `[`DataUtils`](-data-utils/index.html) |
| [PurchasesUpdate](-purchases-update/index.html) | Updated purchase data from BillingClient.onPurchasesUpdated`data class PurchasesUpdate : `[`DataUtils`](-data-utils/index.html) |
| [RxBilling](-rx-billing/index.html) | Billing interface for Google's In-app Billing`class RxBilling` |
| [TimberLogger](-timber-logger/index.html) | Timber log wrapper.`class TimberLogger : `[`Logger`](-logger/index.html) |

### Annotations

| [BillingResponse](-billing-response/index.html) | Possible response codes.`annotation class BillingResponse` |

### Exceptions

| [InAppBillingException](-in-app-billing-exception/index.html) | In App billing error exception`class InAppBillingException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |
| [NoBillingSupportedException](-no-billing-supported-exception/index.html) | Billing not supported exception.`class NoBillingSupportedException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |
| [PurchaseException](-purchase-exception/index.html) | Billing purchase exception.`class PurchaseException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |

