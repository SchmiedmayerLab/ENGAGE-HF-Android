//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.bloodpressure.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.engagehf.R
import com.engagehf.modules.ui.VerticalSpacer
import com.engagehf.modules.ui.theme.Spacings
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.TextStyles
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun BloodPressureDescriptionBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacings.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.understanding_your_blood_pressure),
            style = TextStyles.titleLarge
        )
        VerticalSpacer()
        Text(
            text = stringResource(R.string.blood_pressure_description_part_1),
            style = TextStyles.bodyMedium.copy(textAlign = TextAlign.Center)
        )
        VerticalSpacer(height = Spacings.small)
        Text(
            text = stringResource(R.string.blood_pressure_description_part_2),
            style = TextStyles.bodyMedium.copy(textAlign = TextAlign.Center)
        )
        VerticalSpacer(height = Spacings.small)
        Text(
            text = stringResource(R.string.blood_pressure_description_part_3),
            style = TextStyles.bodyMedium.copy(textAlign = TextAlign.Center)
        )
        VerticalSpacer()
    }
}

@ThemePreviews
@Composable
fun BloodPressureDescriptionBottomSheetPreview() {
    EngageTheme {
        BloodPressureDescriptionBottomSheet()
    }
}
