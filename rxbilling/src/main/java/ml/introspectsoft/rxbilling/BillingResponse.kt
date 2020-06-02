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

package ml.introspectsoft.rxbilling

import androidx.annotation.IntDef

/** Possible response codes.  */
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@IntDef(
        BillingResponse.OK,
        BillingResponse.USER_CANCELED,
        BillingResponse.SERVICE_UNAVAILABLE,
        BillingResponse.BILLING_UNAVAILABLE,
        BillingResponse.ITEM_UNAVAILABLE,
        BillingResponse.DEVELOPER_ERROR,
        BillingResponse.ERROR,
        BillingResponse.ITEM_ALREADY_OWNED,
        BillingResponse.ITEM_NOT_OWNED
)
annotation class BillingResponse {
    companion object {
        /** Success  */
        const val OK = 0

        /** User pressed back or canceled a dialog  */
        const val USER_CANCELED = 1

        /** Network connection is down  */
        const val SERVICE_UNAVAILABLE = 2

        /** Billing API version is not supported for the type requested  */
        const val BILLING_UNAVAILABLE = 3

        /** Requested product is not available for purchase  */
        const val ITEM_UNAVAILABLE = 4

        /**
         * Invalid arguments provided to the API. This error can also indicate that the application was
         * not correctly signed or properly set up for In-app Billing in Google Play, or does not have
         * the necessary permissions in its manifest
         */
        const val DEVELOPER_ERROR = 5

        /** Fatal error during the API action  */
        const val ERROR = 6

        /** Failure to purchase since item is already owned  */
        const val ITEM_ALREADY_OWNED = 7

        /** Failure to consume since item is not owned  */
        const val ITEM_NOT_OWNED = 8
    }
}
