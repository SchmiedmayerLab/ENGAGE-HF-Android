//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.questionnaire

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.engagehf.R
import com.engagehf.modules.questionnaire.QuestionnaireComposable
import com.engagehf.modules.questionnaire.QuestionnaireResult
import com.engagehf.modules.ui.CenteredBoxContent
import com.engagehf.modules.ui.StringResource
import com.engagehf.modules.ui.VerticalSpacer
import com.engagehf.modules.ui.testIdentifier
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.Colors.primary
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun QuestionnaireScreen() {
    val viewModel = hiltViewModel<QuestionnaireViewModel>()
    val uiState by viewModel.uiState.collectAsState()
    QuestionnaireScreen(uiState = uiState, onAction = viewModel::onAction)
}

@Composable
fun QuestionnaireScreen(
    uiState: QuestionnaireViewModel.State,
    onAction: (QuestionnaireViewModel.Action) -> Unit,
) {
    when (uiState) {
        is QuestionnaireViewModel.State.Loading -> {
            LoadingIndicator()
        }

        is QuestionnaireViewModel.State.Error -> {
            CenteredBoxContent {
                Text(
                    modifier = Modifier.testIdentifier(QuestionnaireScreenTestIdentifier.ERROR),
                    text = uiState.message.text(),
                    textAlign = TextAlign.Center,
                    color = Colors.error
                )
            }
        }

        is QuestionnaireViewModel.State.QuestionnaireLoaded -> {
            QuestionnaireLoaded(uiState, onAction)
        }
    }
}

@Composable
private fun LoadingIndicator() {
    CenteredBoxContent {
        CircularProgressIndicator(
            modifier = Modifier.testIdentifier(QuestionnaireScreenTestIdentifier.LOADING),
            color = primary
        )
    }
}

@Composable
private fun QuestionnaireLoaded(
    uiState: QuestionnaireViewModel.State.QuestionnaireLoaded,
    onAction: (QuestionnaireViewModel.Action) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testIdentifier(QuestionnaireScreenTestIdentifier.QUESTIONNAIRE_LOADED)
    ) {
        VerticalSpacer()
        Text(
            text = stringResource(R.string.questionnaire),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        VerticalSpacer()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            QuestionnaireComposable(
                questionnaireJson = uiState.questionnaireString,
                onResult = {
                    when (it) {
                        is QuestionnaireResult.Completed -> {
                            onAction(
                                QuestionnaireViewModel.Action.SaveQuestionnaireResponse(
                                    it.response
                                )
                            )
                        }

                        is QuestionnaireResult.Cancelled -> {
                            onAction(
                                QuestionnaireViewModel.Action.Cancel
                            )
                        }

                        is QuestionnaireResult.Failed -> {
                            println("Failed")
                        }
                    }
                }
            )
            if (uiState.isSaving) LoadingIndicator()
        }
    }
}

enum class QuestionnaireScreenTestIdentifier {
    LOADING,
    ERROR,
    QUESTIONNAIRE_LOADED,
}

private class QuestionnaireScreenPreviewProvider :
    PreviewParameterProvider<QuestionnaireViewModel.State> {
    override val values = sequenceOf(
        QuestionnaireViewModel.State.Loading,
        QuestionnaireViewModel.State.Error(StringResource(R.string.failed_to_load_questionnaire)),
    )
}

@ThemePreviews
@Composable
private fun PreviewQuestionnaireScreen(
    @PreviewParameter(QuestionnaireScreenPreviewProvider::class) uiState: QuestionnaireViewModel.State,
) {
    EngageTheme {
        QuestionnaireScreen(
            uiState = uiState,
            onAction = {}
        )
    }
}
