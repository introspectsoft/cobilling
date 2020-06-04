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

package ml.introspectsoft.cobilling

import java.math.BigDecimal

/**
 * Internal utility functions
 */
internal class Utils {
    companion object {
        private const val MICRO_SCALE = 6
        private const val ROUNDING_DIGITS = 2

        /**
         * Convert from micro units like [Inventory.priceAmountMicros] to BigDecimal
         */
        fun asBigDecimal(micros: Long): BigDecimal {
            return BigDecimal.valueOf(micros, MICRO_SCALE)
                    .setScale(ROUNDING_DIGITS, BigDecimal.ROUND_HALF_UP)
        }
    }
}