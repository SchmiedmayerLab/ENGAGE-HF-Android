//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.medication.ui

import com.engagehf.bluetooth.data.mapper.MessageActionMapper
import com.engagehf.education.EngageEducationRepository
import com.engagehf.medication.data.MedicationRecommendation
import com.engagehf.medication.data.MedicationRepository
import com.engagehf.messages.MessageAction
import com.engagehf.modules.education.EducationNavigationEvent
import com.engagehf.modules.education.videos.Video
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.testing.CoroutineTestRule
import com.engagehf.modules.testing.runTestUnconfined
import com.engagehf.modules.utils.MessageNotifier
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MedicationViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val medicationRepository: MedicationRepository = mockk()
    private val medicationUiStateMapper: MedicationUiStateMapper = mockk()
    private val recommendations: List<MedicationRecommendation> = mockk()
    private val uiModels: List<MedicationCardUiModel> = mockk()
    private val navigator: Navigator = mockk(relaxed = true)
    private val engageEducationRepository: EngageEducationRepository = mockk()
    private val messageActionMapper: MessageActionMapper = mockk()
    private val messageNotifier: MessageNotifier = mockk()

    private lateinit var viewModel: MedicationViewModel

    @Before
    fun setup() {
        coEvery { medicationRepository.observeMedicationRecommendations() } returns flowOf(
            Result.success(recommendations)
        )
        every {
            medicationUiStateMapper.mapMedicationUiState(recommendations)
        } returns MedicationUiState.Success(
            medicationsTaking = Medications(medications = uiModels, expanded = true),
            medicationsThatMayHelp = Medications(medications = uiModels, expanded = true),
            colorKeyExpanded = true
        )
        viewModel = MedicationViewModel(
            medicationRepository,
            medicationUiStateMapper,
            navigator = navigator,
            engageEducationRepository = engageEducationRepository,
            messageActionMapper = messageActionMapper,
            messageNotifier = messageNotifier
        )
    }

    @Test
    fun `given success state when ToggleSectionExpand action then uiState is updated`() =
        runTestUnconfined {
            // given
            val givenExpanded = true
            val initialState = MedicationUiState.Success(
                medicationsTaking = Medications(medications = uiModels, expanded = givenExpanded),
                medicationsThatMayHelp = Medications(medications = uiModels, expanded = true),
                colorKeyExpanded = true
            )
            every {
                medicationUiStateMapper.toggleItemExpand(
                    section = MedicationViewModel.Section.MEDICATIONS_TAKING,
                    uiState = initialState
                )
            } returns initialState.copy(
                medicationsTaking = initialState.medicationsTaking.copy(expanded = false)
            )

            // when
            viewModel.onAction(MedicationViewModel.Action.ToggleSectionExpand(MedicationViewModel.Section.MEDICATIONS_TAKING))

            // then
            val result = viewModel.uiState.value
            assertThat(result).isEqualTo(
                initialState.copy(
                    medicationsTaking = initialState.medicationsTaking.copy(expanded = givenExpanded.not())
                )
            )
        }

    @Test
    fun `given medication details when initialized then uiState is success`() = runTestUnconfined {
        // given
        every {
            medicationUiStateMapper.mapMedicationUiState(recommendations)
        } returns MedicationUiState.Success(
            medicationsTaking = Medications(medications = uiModels, expanded = true),
            medicationsThatMayHelp = Medications(medications = uiModels, expanded = true),
            colorKeyExpanded = true,
        )

        // when
        val uiState = viewModel.uiState.value

        // then
        assertThat(uiState).isInstanceOf(MedicationUiState.Success::class.java)
        assertThat((uiState as MedicationUiState.Success).medicationsTaking.medications).isEqualTo(
            uiModels
        )
        assertThat(uiState.medicationsThatMayHelp.medications).isEqualTo(uiModels)
    }

    @Test
    fun `given success state when expand action then uiState is updated`() = runTestUnconfined {
        // given
        val medicationId = "some-id"
        val toggledResult = MedicationUiState.Success(
            medicationsTaking = Medications(medications = emptyList(), expanded = true),
            medicationsThatMayHelp = Medications(medications = emptyList(), expanded = true),
            colorKeyExpanded = true
        )
        every {
            medicationUiStateMapper.expandMedication(
                medicationId = medicationId,
                uiState = any()
            )
        } returns toggledResult

        // when
        viewModel.onAction(MedicationViewModel.Action.ToggleExpand(medicationId))

        // then
        val result = viewModel.uiState.value
        assertThat(result).isEqualTo(toggledResult)
    }

    @Test
    fun `given infoClicked action when video is loaded then navigate to video section`() =
        runTestUnconfined {
            // given
            val videoPath = "/videoSections/1/videos/1"
            val videoSectionId = "1"
            val actualVideoId = "1"
            val actualVideo = mockk<Video>()
            val mappedAction = mockk<MessageAction.VideoAction> {
                every { sectionId } returns videoSectionId
                every { videoId } returns actualVideoId
            }

            every { messageActionMapper.mapVideoAction(videoPath) } returns Result.success(
                mappedAction
            )
            coEvery {
                engageEducationRepository.getVideoBySectionAndVideoId(
                    videoSectionId,
                    actualVideoId
                )
            } returns Result.success(actualVideo)

            // when
            viewModel.onAction(MedicationViewModel.Action.InfoClicked(videoPath))

            // then
            verify { navigator.navigateTo(EducationNavigationEvent.VideoSectionClicked(actualVideo)) }
        }
}
