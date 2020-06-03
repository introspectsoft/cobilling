---
title: RxBilling.isBillingForSubscriptionsSupported - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [RxBilling](index.html) / [isBillingForSubscriptionsSupported](./is-billing-for-subscriptions-supported.html)

# isBillingForSubscriptionsSupported

`val isBillingForSubscriptionsSupported: @NonNull Completable`

Checks whether billing for subscriptions is supported or not.
In case it is the Completable will just complete.
Otherwise a [NoBillingSupportedException](../-no-billing-supported-exception/index.html) will be thrown.

**Return**
Completable which will complete in case it is supported. Otherwise an error will be emitted.

