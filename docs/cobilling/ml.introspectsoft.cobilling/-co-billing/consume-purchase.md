---
title: CoBilling.consumePurchase - cobilling
---

[cobilling](../../index.html) / [ml.introspectsoft.cobilling](../index.html) / [CoBilling](index.html) / [consumePurchase](./consume-purchase.html)

# consumePurchase

`suspend fun consumePurchase(purchased: Purchase): ConsumeResult`

Consumes the in app purchase.
Purchases not acknowledged or consumed after 3 days are refunded.

### Parameters

`purchased` - the purchase to consume

**Return**
ConsumeResult(BillingResult, purchaseToken : String)

