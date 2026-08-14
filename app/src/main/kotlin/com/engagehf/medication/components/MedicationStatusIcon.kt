//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.medication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.engagehf.medication.ui.MedicationCardUiModel
import com.engagehf.modules.ui.theme.Sizes
import com.engagehf.modules.ui.theme.Spacings
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun MedicationStatusIcon(model: MedicationCardUiModel) {
    val backgroundColor = model.statusColor.value
    Box(
        modifier = Modifier
            .size(Sizes.Icon.medium)
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .padding(Spacings.small),
        contentAlignment = Alignment.Center
    ) {
        model.statusIconResId?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = backgroundColor,
                modifier = Modifier
                    .size(Sizes.Icon.medium)
                    .padding(Spacings.small)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun MedicationStatusIconPreview(
    @PreviewParameter(MedicationCardModelsProvider::class) model: MedicationCardUiModel,
) {
    EngageTheme {
        MedicationStatusIcon(model = model)
    }
}
