//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.bloodpressure

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.engagehf.health.HealthPage
import com.engagehf.health.RecordType
import com.engagehf.health.healthRecordViewModel

@Composable
fun BloodPressurePage() {
    val viewModel = healthRecordViewModel(type = RecordType.BLOOD_PRESSURE)
    val uiState by viewModel.uiState.collectAsState()
    HealthPage(uiState = uiState, onAction = viewModel::onAction)
}
