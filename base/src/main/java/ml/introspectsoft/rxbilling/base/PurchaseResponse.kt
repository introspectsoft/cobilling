/*
 * Copyright (c) 2018 Vanniktech - Niklas Baudy
 * Modifications Copyright (c) 2020. Jason Burgess
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

package ml.introspectsoft.rxbilling.base

/** @return The item's product identifier. Every item has a product ID, which you must specify in the application's product list on the Google Play Console.
 */
/** @return A token that uniquely identifies a purchase for a given item and user pair.
 */
/** @return The purchase state of the order.
 */
/** @return The time the product was purchased, in milliseconds since the epoch (Jan 1, 1970).
 */

data class PurchaseResponse(
    val packageName: String,
    val productId: String,
    val purchaseToken: String,
    @ml.introspectsoft.rxbilling.base.RxBilling.BillingResponse
    val purchaseState: Int,
    val purchaseTime: Long
) {
}
