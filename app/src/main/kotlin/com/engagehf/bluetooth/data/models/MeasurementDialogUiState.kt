//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.data.models

import com.engagehf.bluetooth.service.Measurement

data class MeasurementDialogUiState(
    val measurement: Measurement? = null,
    val isVisible: Boolean = false,
    val isProcessing: Boolean = false,
    val formattedWeight: String = "",
    val formattedSystolic: String = "",
    val formattedDiastolic: String = "",
    val formattedHeartRate: String = "",
)
