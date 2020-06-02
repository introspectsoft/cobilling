[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [Logger](./index.md)

# Logger

`interface Logger`

Wrapper to the user's preferred Log function

### Functions

| [d](d.md) | `abstract fun d(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [e](e.md) | `abstract fun e(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [w](w.md) | `abstract fun w(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun w(throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [LogcatLogger](../-logcat-logger/index.md) | Wrapper for Logcat logging.`class LogcatLogger : `[`Logger`](./index.md) |
| [TimberLogger](../-timber-logger/index.md) | Timber log wrapper.`class TimberLogger : `[`Logger`](./index.md) |

