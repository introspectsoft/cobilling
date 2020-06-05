---
title: CoBilling.acknowledgePurchase - cobilling
---

[cobilling](../../index.html) / [ml.introspectsoft.cobilling](../index.html) / [CoBilling](index.html) / [acknowledgePurchase](./acknowledge-purchase.html)

# acknowledgePurchase

`suspend fun acknowledgePurchase(purchased: Purchase): BillingResult`

Acknowledge the purchase
Purchases not acknowledged or consumed after 3 days are refunded.

### Parameters

`purchased` - the purchase to acknowledge

**Return**
BillingResult

