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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class UtilsTest {
    private val inputs = listOf(0L, 990000L, 7842985L, 199999999L)
    private val outputs =
            listOf(BigDecimal("0.00"), BigDecimal("0.99"), BigDecimal("7.84"), BigDecimal("200.00"))

    @Test
    fun asBigDecimal() {
        repeat(inputs.size) { i ->
            assertEquals(outputs[i], Utils.asBigDecimal(inputs[i]))
        }
    }
}