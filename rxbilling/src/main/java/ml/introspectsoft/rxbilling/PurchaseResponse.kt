/*
 * Copyright (c) 2020. Jason Burgess
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.introspectsoft.rxbilling

import com.android.billingclient.api.Purchase

/**
 * Data class for handling purchase history data
 *
 * @param[packageName] package that originated the purchase
 * @param[productId] product sku
 * @param[purchaseToken] unique identifier for the purchase.
 * @param[purchaseState] purchase state of the order. Should verify it is [PurchaseState.PURCHASED][https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#purchased]
 *                       and not [PurchaseState.PENDING][https://developer.android.com/reference/com/android/billingclient/api/Purchase.PurchaseState#pending]
 * @param[purchaseTime] purchase time in milliseconds
 * @param[purchaseOrderId] order id of the purchase
 * @param[isAcknowledged] is the purchase acknowledged
 * @param[isAutoRenewing] is the purchase auto renewing
 * @constructor Create a new PurchaseResponse
 */
data class PurchaseResponse(
        val packageName: String,
        val productId: String,
        val purchaseToken: String,
        @BillingResponse val purchaseState: Int = 0,
        val purchaseTime: Long = 0,
        val purchaseOrderId: String,
        val isAcknowledged: Boolean = false,
        val isAutoRenewing: Boolean = false
) : DataUtils {
    /**
     * Create a new PurchaseResponse
     *
     * @param[purchase] Purchase object to convert to data
     */
    constructor(purchase: Purchase) : this(
            purchase.packageName,
            purchase.sku,
            purchase.purchaseToken,
            purchase.purchaseState,
            purchase.purchaseTime,
            purchase.orderId,
            purchase.isAcknowledged,
            purchase.isAutoRenewing
    )
}
