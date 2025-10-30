/*
 * Copyright (c) 2024 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.app.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.*
import kotlin.random.Random

object VehicleDataSimulator {
    private val scope = CoroutineScope(Dispatchers.Default + Job())
    private val _vehicleState = MutableStateFlow(generateRandomState())
    val vehicleState: StateFlow<VehicleState> = _vehicleState

    init {
        scope.launch {
            while (isActive) {
                _vehicleState.value = generateRandomState()
                delay(1000)
            }
        }
    }
    private fun generateRandomState() = VehicleState(
        speed = Random.nextInt(0, 130),
        batteryLevel = Random.nextInt(10, 100),
        temperature = Random.nextInt(-10, 40)
    )
}
