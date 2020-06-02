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

package ml.introspectsoft.google_play_library

import ml.introspectsoft.rxbilling.base.Logger
import timber.log.Timber

internal class TimberLogger : Logger {
    override fun d(log: String) {
        Timber.d(log)
    }

    override fun w(log: String) {
        Timber.w(log)
    }

    override fun w(throwable: Throwable) {
        Timber.w(throwable)
    }

    override fun e(log: String) {
        Timber.e(log)
    }
}