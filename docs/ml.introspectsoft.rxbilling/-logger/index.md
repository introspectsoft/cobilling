---
title: Logger - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [Logger](./index.html)

# Logger

`interface Logger`

Wrapper to the user's preferred Log function

### Functions

| [d](d.html) | `abstract fun d(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [e](e.html) | `abstract fun e(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [w](w.html) | `abstract fun w(log: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`abstract fun w(throwable: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| [LogcatLogger](../-logcat-logger/index.html) | Wrapper for Logcat logging.`class LogcatLogger : `[`Logger`](./index.html) |
| [TimberLogger](../-timber-logger/index.html) | Timber log wrapper.`class TimberLogger : `[`Logger`](./index.html) |

