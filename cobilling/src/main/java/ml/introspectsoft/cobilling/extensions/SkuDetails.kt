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

package ml.introspectsoft.cobilling.extensions

import com.android.billingclient.api.SkuDetails
import ml.introspectsoft.cobilling.Utils
import java.math.BigDecimal
import java.util.*

/**
 * @return [Currency] for price. (localized)
 */
val SkuDetails.priceCurrency: Currency
    get() = Currency.getInstance(priceCurrencyCode)

/**
 * @return price as BigDecimal. (localized)
 */
val SkuDetails.priceAsBigDecimal: BigDecimal
    get() = Utils.asBigDecimal(
            priceAmountMicros
    )