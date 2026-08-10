//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.health.time

import java.time.Instant
import java.time.LocalTime

data class TimePickerState(
    val selectedDate: Instant,
    val selectedTime: LocalTime,
    val initialHour: Int,
    val initialMinute: Int,
    val selectedDateFormatted: String,
    val selectedTimeFormatted: String,
)
