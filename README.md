# CoBilling
Kotlin coroutines wrappers for the Google Play Billing API.
- coroutines - 2.3.0-alpha03
- Google Play Billing - 2.2.1.

***NOTE:*** This library is still in progress and is not yet ready for production use.

# Usage

[Library documentation](https://introspectsoft.github.io/cobilling/cobilling)

The [CommonUsage](/examples/src/main/java/ml/introspectsoft/examples/CommonUsages.kt) example activity shows some common use cases.

## Gradle

```groovy
implementation 'ml.introspectsoft.cobilling:cobilling:0.3.1-alpha'
```

## Kotlin

```kotlin
class YourActivity : Activity() {
    private var billing: CoBilling? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        billing = CoBilling(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        billing?.close()
    }
}
```

# License

Copyright &copy; 2020 Jason Burgess 

Sample code included in [examples] is released under the [Zero-Clause BSD](sampleapplication/LICENSE) license.

All other code is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
