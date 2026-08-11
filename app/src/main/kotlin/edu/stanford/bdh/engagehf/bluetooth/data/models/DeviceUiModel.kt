//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.bluetooth.data.models

import edu.stanford.spezi.ui.StringResource

data class DeviceUiModel(
    val name: String,
    val summary: StringResource,
    val connected: Boolean,
    val lastSeen: StringResource,
)
