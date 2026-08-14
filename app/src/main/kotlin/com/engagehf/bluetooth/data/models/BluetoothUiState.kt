//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.data.models

import com.engagehf.R
import edu.stanford.spezi.ui.StringResource

sealed interface BluetoothUiState {
    data class Idle(
        val description: StringResource = StringResource(R.string.bluetooth_not_enabled_description),
        val settingsAction: Action.Settings? = null,
    ) : BluetoothUiState

    data class Ready(val header: StringResource?, val devices: List<DeviceUiModel>) : BluetoothUiState
}
