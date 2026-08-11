//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.health.weight.bottomsheet

import edu.stanford.bdh.engagehf.health.time.TimePickerState

data class AddWeightBottomSheetUiState(
    val timePickerState: TimePickerState,
    val weight: Double = 80.0,
    val weightUnit: WeightUnit,
) {
    @Suppress("MagicNumber")
    val weightRange = 0..400
}

enum class WeightUnit {
    KG,
    LBS,
}
