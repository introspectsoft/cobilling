[rxbilling](../../index.md) / [ml.introspectsoft.rxbilling](../index.md) / [RxBilling](index.md) / [isBillingForSubscriptionsSupported](./is-billing-for-subscriptions-supported.md)

# isBillingForSubscriptionsSupported

`val isBillingForSubscriptionsSupported: @NonNull Completable`

Checks whether billing for subscriptions is supported or not.
In case it is the Completable will just complete.
Otherwise a [NoBillingSupportedException](../-no-billing-supported-exception/index.md) will be thrown.

**Return**
Completable which will complete in case it is supported. Otherwise an error will be emitted.

