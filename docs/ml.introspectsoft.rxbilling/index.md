[rxbilling](../index.md) / [ml.introspectsoft.rxbilling](./index.md)

## Package ml.introspectsoft.rxbilling

### Types

| [DataUtils](-data-utils/index.md) | Extension functions for data classes`interface DataUtils` |
| [Inventory](-inventory/index.md) | Data class for handling items to be purchased.`data class Inventory : `[`DataUtils`](-data-utils/index.md) |
| [LogcatLogger](-logcat-logger/index.md) | Wrapper for Logcat logging.`class LogcatLogger : `[`Logger`](-logger/index.md) |
| [Logger](-logger/index.md) | Wrapper to the user's preferred Log function`interface Logger` |
| [PurchaseResponse](-purchase-response/index.md) | Data class for handling purchase history data`data class PurchaseResponse : `[`DataUtils`](-data-utils/index.md) |
| [PurchasesUpdate](-purchases-update/index.md) | Updated purchase data from BillingClient.onPurchasesUpdated`data class PurchasesUpdate : `[`DataUtils`](-data-utils/index.md) |
| [RxBilling](-rx-billing/index.md) | Billing interface for Google's In-app Billing`class RxBilling` |
| [TimberLogger](-timber-logger/index.md) | Timber log wrapper.`class TimberLogger : `[`Logger`](-logger/index.md) |

### Annotations

| [BillingResponse](-billing-response/index.md) | Possible response codes.`annotation class BillingResponse` |

### Exceptions

| [InAppBillingException](-in-app-billing-exception/index.md) | In App billing error exception`class InAppBillingException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |
| [NoBillingSupportedException](-no-billing-supported-exception/index.md) | Billing not supported exception.`class NoBillingSupportedException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |
| [PurchaseException](-purchase-exception/index.md) | Billing purchase exception.`class PurchaseException : `[`RuntimeException`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-runtime-exception/index.html) |

