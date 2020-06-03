# RxBilling2
RxJava wrapper for the Google Play Billing API. Uses RxJava3 - 3.0.4 and Google Play Billing - 2.2.1.

Based on the [RxBilling library by Vanniktech - Niklas Baudy](https://github.com/vanniktech/RxBilling).
Updated for RxJava3 and Google Play Billing API v2. Also rewritten completely in Kotlin.

The deprecated AIDL interface from the original library has been removed and all functionality has
been combined into one package.

***NOTE:*** This library is still in progress and is not yet ready for production use.

# Usage

Library documentation is available on [https://introspectsoft.github.io/RxBilling2/].

Core functionality is provided in the [RxBilling](https://introspectsoft.github.io/RxBilling2/ml.introspectsoft.rxbilling/-rx-billing/) class.

## Gradle

```groovy
implementation 'ml.introspectsoft.rxbilling:rxbilling2:0.2.0-experimental'
```

## Kotlin

```kotlin
class YourActivity : Activity() {
    private var rxBilling: RxBilling? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate()
        rxBilling = RxBilling(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        rxBilling!!.destroy()
    }
}
```

## Java

```java
class YourActivity extends Activity {
  private RxBilling rxBilling;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate();
    rxBilling = new RxBilling(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    rxBilling.destroy();
  }
}
```

# License

Copyright &copy; 2018 Vanniktech - Niklas Baudy
Modifications copyright &copy; 2020 Jason Burgess 

Licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
