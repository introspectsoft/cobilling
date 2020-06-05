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

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StringKtTest {
    private val testStrings = listOf(
            "",
            "8675309",
            "Lorem ipsum dolor sit amet",
            "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"
    )
    private val resultStringsSha256 = listOf(
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            "37ba3881108bf3e48180350246c5959b9481633d0cb1d8694fb141dc74e5fe79",
            "16aba5393ad72c0041f5600ad3c2c52ec437a2f0c7fc08fadfc3c0fe9641d7a3",
            "361d1ba46b2929efe8aa953e32de3da0dd9425f4d8c739edba12a92eaa7d5b60"
    )
    private val resultStringsMD5 =
            listOf(
                    "d41d8cd98f00b204e9800998ecf8427e",
                    "6cc0d36686e6a433aa76f96773852d35",
                    "fea80f2db003d4ebc4536023814aa885",
                    "2d0730fc89510d1aec24e16bfb92d69b"
            )

    @Test
    @DisplayName("Hash string to SHA-256")
    fun toSha256() {
        repeat(testStrings.size) {
            assertEquals(resultStringsSha256[it], testStrings[it].toSha256())
        }
    }

    @Test
    @DisplayName("Hash string to MD5")
    fun toMD5() {
        repeat(testStrings.size) {
            assertEquals(resultStringsMD5[it], testStrings[it].toMD5())
        }
    }
}