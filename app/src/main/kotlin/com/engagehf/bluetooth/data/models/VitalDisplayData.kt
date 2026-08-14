//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.bluetooth.data.models

import com.engagehf.bluetooth.component.OperationStatus
import com.engagehf.modules.ui.StringResource

data class VitalDisplayData(
    val title: StringResource,
    val value: String? = null,
    val unit: String? = null,
    val date: String? = null,
    val status: OperationStatus = OperationStatus.PENDING,
    val error: String? = null,
)
