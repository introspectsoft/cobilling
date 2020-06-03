---
title: RxBilling.acknowledgePurchase - rxbilling
---

[rxbilling](../../index.html) / [ml.introspectsoft.rxbilling](../index.html) / [RxBilling](index.html) / [acknowledgePurchase](./acknowledge-purchase.html)

# acknowledgePurchase

`@CheckReturnValue fun acknowledgePurchase(purchased: Purchase): Single<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>`

Acknowledge the given InApp purchase which has been bought.
Purchases not acknowledged or consumed after 3 days are refunded.

### Parameters

`purchased` - the purchased in app purchase to consume

**Return**
Single containing the BillingResponse

