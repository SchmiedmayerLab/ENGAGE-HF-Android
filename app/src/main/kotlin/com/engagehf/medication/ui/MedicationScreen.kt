//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.medication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.engagehf.R
import com.engagehf.medication.components.LoadingMedicationSection
import com.engagehf.medication.components.MedicationList
import com.engagehf.medication.components.getMedicationCardUiModel
import com.engagehf.modules.ui.CenteredBoxContent
import com.engagehf.modules.ui.RepeatingLazyColumn
import com.engagehf.modules.ui.StringResource
import com.engagehf.modules.ui.testIdentifier
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.Spacings
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.TextStyles
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun MedicationScreen() {
    val viewModel = hiltViewModel<MedicationViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    MedicationScreen(
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
fun MedicationScreen(
    uiState: MedicationUiState,
    onAction: (MedicationViewModel.Action) -> Unit,
) {
    when (uiState) {
        is MedicationUiState.Error -> {
            CenteredBoxContent {
                Text(
                    text = uiState.message.text(),
                    color = Colors.error,
                    style = TextStyles.titleMedium,
                    modifier = Modifier.testIdentifier(MedicationScreenTestIdentifier.ERROR_TEXT),
                )
            }
        }

        is MedicationUiState.NoData -> {
            CenteredBoxContent {
                Text(
                    text = uiState.message.text(),
                    style = TextStyles.titleMedium,
                    modifier = Modifier.testIdentifier(MedicationScreenTestIdentifier.NO_DATA_TEXT),
                )
            }
        }

        MedicationUiState.Loading -> {
            RepeatingLazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Spacings.medium)
                    .testIdentifier(MedicationScreenTestIdentifier.LOADING),
                itemCount = 2,
                content = { LoadingMedicationSection() }
            )
        }

        is MedicationUiState.Success -> {
            MedicationList(
                uiState = uiState,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Spacings.medium)
                    .testIdentifier(MedicationScreenTestIdentifier.SUCCESS)
            )
        }
    }
}

private class UiStateProvider : PreviewParameterProvider<MedicationUiState> {
    override val values: Sequence<MedicationUiState> = sequenceOf(
        MedicationUiState.Loading,
        MedicationUiState.Error(message = StringResource(R.string.generic_error_description)),
        MedicationUiState.NoData(message = StringResource(R.string.no_messages)),
        MedicationUiState.Success(
            medicationsTaking = Medications(
                listOf(
                    getMedicationCardUiModel(MedicationColor.YELLOW, true),
                    getMedicationCardUiModel(MedicationColor.GREEN_SUCCESS, true),
                ), expanded = true
            ),
            medicationsThatMayHelp = Medications(
                listOf(
                    getMedicationCardUiModel(MedicationColor.BLUE),
                ),
                expanded = false
            ),
            colorKeyExpanded = false
        )
    )
}

enum class MedicationScreenTestIdentifier {
    LOADING,
    ERROR_TEXT,
    NO_DATA_TEXT,
    SUCCESS,
    SUCCESS_MEDICATION_CARD_ROOT,
    SUCCESS_MEDICATION_CARD_TITLE,
    SUCCESS_MEDICATION_CARD_SUBTITLE,
    SUCCESS_MEDICATION_CARD_DESCRIPTION,
}

@ThemePreviews
@Composable
private fun MedicationScreenPreview(@PreviewParameter(UiStateProvider::class) uiState: MedicationUiState) {
    EngageTheme {
        MedicationScreen(
            uiState = uiState,
            onAction = { }
        )
    }
}
