# CoBilling
Kotlin coroutines wrappers for the Google Play Billing API.
- coroutines - 2.3.0-alpha03
- Google Play Billing - 2.2.1.

***NOTE:*** This library is still in progress and is not yet ready for production use.

# Usage

[Library documentation](https://introspectsoft.github.io/cobilling/cobilling)

Core functionality is provided in the [CoBilling](https://introspectsoft.github.io/cobilling/cobilling/ml.introspectsoft.cobilling/-co-billing/) class.

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

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
