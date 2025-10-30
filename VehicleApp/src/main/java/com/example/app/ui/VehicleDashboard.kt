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

package com.example.app.ui

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.app.model.VehicleDataSimulator

@Composable
fun VehicleDashboard() {
    val state by VehicleDataSimulator.vehicleState.collectAsState()
    Column(modifier = Modifier.padding(24.dp)) {
        Card(modifier = Modifier.padding(8.dp)) {
            Text("Speed: ${state.speed} km/h", style = MaterialTheme.typography.h5)
        }
        Card(modifier = Modifier.padding(8.dp)) {
            Text("Battery: ${state.batteryLevel}%", style = MaterialTheme.typography.h5)
        }
        Card(modifier = Modifier.padding(8.dp)) {
            Text("Temperature: ${state.temperature}°C", style = MaterialTheme.typography.h5)
        }
    }
}
