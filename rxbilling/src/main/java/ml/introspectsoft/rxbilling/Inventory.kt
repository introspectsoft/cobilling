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

import com.android.billingclient.api.SkuDetails
import java.math.BigDecimal
import java.util.*

/**
 * Data class for handling items to be purchased.
 *
 * @param[sku] product sku
 * @param[type] type of product. Will be SkuType.INAPP or SkuType.SUBS
 * @param[price] formatted price of item with currency symbol. (localized)
 * @param[priceAmountMicros] price in micro units (1,000,000 = 1 currency unit) (localized)
 * @param[priceCurrencyCode] ISO 4217 currency code (localized)
 * @param[title] product name
 * @param[description] product description
 * @param[skuDetails] SkuDetails object it was created from, if any
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
) : DataUtils {
    constructor(details: SkuDetails) : this(
            details.sku,
            details.type,
            details.price,
            details.priceAmountMicros,
            details.priceCurrencyCode,
            details.title,
            details.description,
            details
    )

    /**
     * @return [Currency] for price. (localized)
     */
    val priceCurrency: Currency
        get() = Currency.getInstance(priceCurrencyCode)

    /**
     * @return price as BigDecimal. (localized)
     */
    val priceAsBigDecimal: BigDecimal
        get() = Utils.asBigDecimal(
                priceAmountMicros
        )
}