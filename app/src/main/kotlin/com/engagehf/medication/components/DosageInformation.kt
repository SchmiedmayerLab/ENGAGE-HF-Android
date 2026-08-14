//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.medication.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.engagehf.medication.ui.DosageInformationUiModel
import com.engagehf.medication.ui.DosageRowInfoData
import com.engagehf.modules.ui.VerticalSpacer
import com.engagehf.modules.ui.theme.SpeziTheme
import com.engagehf.modules.ui.theme.TextStyles
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun DosageInformation(dosageInformationUiModel: DosageInformationUiModel) {
    Column {
        DosageInfoRow(dosageRowInfoData = dosageInformationUiModel.currentDose)
        VerticalSpacer()
        DosageInfoRow(dosageRowInfoData = dosageInformationUiModel.targetDose)
    }
}

@Composable
fun DosageInfoRow(dosageRowInfoData: DosageRowInfoData) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(
                text = dosageRowInfoData.label,
                style = TextStyles.labelLarge
            )
        }
        Column {
            dosageRowInfoData.dosageValues.forEach { dose ->
                Text(
                    text = dose,
                    style = TextStyles.bodyMedium
                )
            }
        }
    }
}

private class DosageInformationProvider : PreviewParameterProvider<DosageInformationUiModel> {
    override val values: Sequence<DosageInformationUiModel> = sequenceOf(
        DosageInformationUiModel(
            currentDose = DosageRowInfoData(
                label = "Current Dose:",
                dosageValues = listOf(
                    "1.0 mg daily",
                    "2.0 mg daily",
                )
            ),
            targetDose = DosageRowInfoData(
                label = "Target Dose:",
                dosageValues = listOf(
                    "1.0 mg daily",
                )
            ),
            progress = 0.234f,
        )
    )
}

@ThemePreviews
@Composable
private fun DoseInformationPreview(
    @PreviewParameter(DosageInformationProvider::class) dosageInformation: DosageInformationUiModel,
) {
    SpeziTheme {
        DosageInformation(dosageInformationUiModel = dosageInformation)
    }
}
