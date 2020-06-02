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

import com.android.billingclient.api.SkuDetails
import java.math.BigDecimal
import java.util.*

/** @return The product ID for the product.
 */
/** @return Value must be inapp for an in-app product or subs for subscriptions.
 */
/** @return Formatted price of the item, including its currency sign. The price does not include tax.
 */
/**
 * @return Price in micro-units, where 1,000,000 micro-units equal one unit of the currency.
 * For example, if price is "€7.99", price_amount_micros is "7990000".
 * This value represents the localized, rounded price for a particular currency.
 */
/**
 * @return ISO 4217 currency code for price.
 * For example, if price is specified in British pounds sterling, priceCurrencyCode is "GBP".
 */
/** @return Title of the product.
 */

/** @return Description of the product.
 */

data class Inventory(
    val sku: String,
    val type: String,
    val price: String,
    val priceAmountMicros: Long,
    val priceCurrencyCode: String,
    val title: String,
    val description: String,
    val skuDetails: SkuDetails? = null
) {
    /**
     * @return [Currency] for price.
     * For example, if price is specified in British pounds sterling, priceCurrency is equal to `Currency.getInstance("GBP")`.
     */
    val priceCurrency: Currency
        get() = Currency.getInstance(priceCurrencyCode)

    /**
     * @return Price as BigDecimal.
     * This value represents the localized, rounded price for a particular currency.
     */
    val priceAsBigDecimal: BigDecimal
        get() = Utils.asBigDecimal(priceAmountMicros)
}
