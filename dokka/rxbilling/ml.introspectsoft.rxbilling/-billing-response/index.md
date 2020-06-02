[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [BillingResponse](./index.md)

# BillingResponse

`annotation class BillingResponse`

Possible response codes.

### Constructors

| [&lt;init&gt;](-init-.md) | Possible response codes.`BillingResponse()` |

### Companion Object Properties

| [BILLING_UNAVAILABLE](-b-i-l-l-i-n-g_-u-n-a-v-a-i-l-a-b-l-e.md) | Billing API version is not supported for the type requested`const val BILLING_UNAVAILABLE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [DEVELOPER_ERROR](-d-e-v-e-l-o-p-e-r_-e-r-r-o-r.md) | Invalid arguments provided to the API. This error can also indicate that the application was not correctly signed or properly set up for In-app Billing in Google Play, or does not have the necessary permissions in its manifest`const val DEVELOPER_ERROR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [ERROR](-e-r-r-o-r.md) | Fatal error during the API action`const val ERROR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [ITEM_ALREADY_OWNED](-i-t-e-m_-a-l-r-e-a-d-y_-o-w-n-e-d.md) | Failure to purchase since item is already owned`const val ITEM_ALREADY_OWNED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [ITEM_NOT_OWNED](-i-t-e-m_-n-o-t_-o-w-n-e-d.md) | Failure to consume since item is not owned`const val ITEM_NOT_OWNED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [ITEM_UNAVAILABLE](-i-t-e-m_-u-n-a-v-a-i-l-a-b-l-e.md) | Requested product is not available for purchase`const val ITEM_UNAVAILABLE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [OK](-o-k.md) | Success`const val OK: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [SERVICE_UNAVAILABLE](-s-e-r-v-i-c-e_-u-n-a-v-a-i-l-a-b-l-e.md) | Network connection is down`const val SERVICE_UNAVAILABLE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [USER_CANCELED](-u-s-e-r_-c-a-n-c-e-l-e-d.md) | User pressed back or canceled a dialog`const val USER_CANCELED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

