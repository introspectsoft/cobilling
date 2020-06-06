/*
 * Copyright (c) 2020 Jason Burgess
 *
 * Permission to use, copy, modify, and/or distribute this software
 * for any purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS
 * ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING
 * ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN
 * NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 * WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package ml.introspectsoft.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.receiveOrNull
import ml.introspectsoft.cobilling.CoBilling

/**
 * An example of how to use the library in an activity with a minimum of extra code.
 */
class CommonUsages : AppCompatActivity() {
    /**
     * Initialize the CoBilling object
     * It needs a reference to the activity to operate because of the way the Google Play Billing API works.
     */
    private val billing: CoBilling = CoBilling(this)

    /**
     * Hook a listener to onPurchasesUpdated onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receiver = billing.purchasesUpdated.openSubscription()
        lifecycleScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            // A simple looping receiver coroutine to listen for new purchases
            while (!receiver.isClosedForReceive) {
                val purchases = receiver.receiveOrNull()
                if (purchases == null) {
                    // null if the channel closed on us, so...
                    cancel()
                }
                purchases?.let { onPurchasesUpdated(it) }
            }
        }
    }

    /**
     * Handle new purchases
     *
     * This is a suspend and is called from inside and existing coroutine
     */
    private suspend fun onPurchasesUpdated(update: Purchase.PurchasesResult) {
        if (update.billingResult.responseCode != BillingClient.BillingResponseCode.OK) {
            // Something went wrong. We really should only get OK results
            return
        }

        update.purchasesList.orEmpty().forEach { purchase ->
            if (purchase.isAcknowledged) {
                // This has already been dealt with and we probably don't need to do anything.
                return@forEach
            }

            if (purchase.purchaseState == Purchase.PurchaseState.PENDING) {
                // Probably just informational pending purchase notification.
                return@forEach
            }

            if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                // An example of how to handle two SKUs different ways
                when (purchase.sku) {
                    "product_to_acknowledge" -> {
                        // Acknowledge the purchase. Purchases not acknowledged here or with the
                        // server API within 3 days are cancelled.
                        val result = billing.acknowledgePurchase(purchase)

                        if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                            // Purchase is fully successful and processed, now we can act on it.
                        } else {
                            // Something went wrong on the acknowledgement.
                        }
                    }
                    "product_to_consume"    -> {
                        // Consume the purchase (which can then be bought again)
                        val result = billing.consumePurchase(purchase)

                        if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            // Purchase is fully successful and processed, now we can act on it.
                        } else {
                            // Something went wrong on the acknowledgement.
                        }
                    }
                    else       -> {
                        // Got a purchase we didn't expect.
                    }
                }
            }
        }
    }

    /**
     * Have to make sure we do this somewhere. Often in the onDestroy method of the Activity
     */
    override fun onDestroy() {
        super.onDestroy()

        // Clean up after ourselves
        billing.close()
    }

    /**
     * Query for SkuDetails given a specific SKU.
     */
    fun queryProductBySku(sku: String) = runBlocking<SkuDetails?> {
        // We could run this in the background with launch, but we want the result
        // before we continue, so we called it with runBlocking
        val result = billing.queryInAppPurchases(sku)

        // Or if we're looking for a subscription
        val subscriptionResult = billing.querySubscriptions(sku)

        // Make sure we got an OK result from the Play Store first.
        // this is the long way with all the extra checks
        if (result.billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            if (!result.skuDetailsList.isNullOrEmpty()) {
                result.skuDetailsList.orEmpty().forEach { skuDetails ->
                    if (skuDetails.sku == sku) {
                        // We found our result
                        return@runBlocking skuDetails
                    }
                }

            }
        }

        // If nothing returned earlier, return null to indicate failure
        return@runBlocking null
    }

    /**
     * Start purchase of SKU
     *
     */
    fun purchaseSku(sku: String) {
        runBlocking {
            var purchaseItem: SkuDetails? = null

            // This is essential the same SkuDetails query from above, but with less code.
            val search = billing.queryInAppPurchases(sku)
            search.skuDetailsList.orEmpty().forEach {
                if (it.sku == sku) {
                    purchaseItem = it
                }
            }

            // See if we got a result
            if (purchaseItem == null) {
                // Didn't find anything so we cancel the coroutine
                this.cancel()
            }

            purchaseItem?.let {
                // Result can be used for displaying messages on failure
                // Also, OK does *not* mean the purchase was successful. It may be pending still.
                val result = billing.purchase(it)
            }
        }
    }
}