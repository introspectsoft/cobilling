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

package ml.introspectsoft.cobilling

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase

/**
 * Updated purchase data from BillingClient.onPurchasesUpdated
 *
 * @param[result] BillingResult of response
 * @param[purchases] list of changed purchases to process
 */
data class PurchasesUpdate(
        val result: BillingResult, val purchases: List<Purchase>?
) : DataUtils {
    constructor(purchasesResult: Purchase.PurchasesResult) : this(purchasesResult.billingResult, purchasesResult.purchasesList)
}