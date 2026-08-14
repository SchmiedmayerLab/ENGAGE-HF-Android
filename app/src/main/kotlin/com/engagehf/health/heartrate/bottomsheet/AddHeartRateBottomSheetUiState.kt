//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.heartrate.bottomsheet

import com.engagehf.health.time.TimePickerState

data class AddHeartRateBottomSheetUiState(
    val timePickerState: TimePickerState,
    val heartRate: Int = 60,
) {
    @Suppress("MagicNumber")
    val heartRateRange = 0..200
}
