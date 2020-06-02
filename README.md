# RxBilling2
RxJava wrapper for the Google Play Billing API. Uses RxJava3 - 3.0.4 and Google Play Billing - 2.2.1.

Based on the [RxBilling library by Vanniktech - Niklas Baudy](https://github.com/vanniktech/RxBilling).
Updated for RxJava3 and Google Play Billing API v2. Also rewritten completely in Kotlin.

The deprecated AIDL interface from the original library has been removed.

***NOTE:*** Documentation below this point is mostly unchanged from the original and therefore not
correct.

***ALSO NOTE:*** This library is still in progress and is not yet usable.

# Usage

The core functionality is provided via an interface:

```java
public interface RxBilling {
  Observable<InventoryInApp> queryInAppPurchases(String... skuIds);

  Observable<InventorySubscription> querySubscriptions(String... skuIds);

  Completable isBillingForInAppSupported();

  Completable isBillingForSubscriptionsSupported();

  Single<PurchaseResponse> purchase(Inventory inventory, String developerPayload);

  Observable<PurchasedInApp> getPurchasedInApps();

  Observable<PurchasedSubscription> getPurchasedSubscriptions();

  Single<Integer> consumePurchase(PurchasedInApp purchasedInApp);

  void destroy();

  @interface BillingResponse {
    int OK = 0;
    int USER_CANCELED = 1;
    int SERVICE_UNAVAILABLE = 2;
    int BILLING_UNAVAILABLE = 3;
    int ITEM_UNAVAILABLE = 4;
    int DEVELOPER_ERROR = 5;
    int ERROR = 6;
    int ITEM_ALREADY_OWNED = 7;
    int ITEM_NOT_OWNED = 8;
  }
}
```

The actual [interface](rxbilling/src/main/java/com/vanniktech/rxbilling/RxBilling.java) also contains documentation.

### Google Play Billing Library implementation

```groovy
implementation 'com.vanniktech:rxbilling-google-play-library:0.3.0'
```

```java
class YourActivity extends Activity {
  private RxBilling rxBilling;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate();
    rxBilling = new RxBillingGooglePlayLibrary(this);
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
