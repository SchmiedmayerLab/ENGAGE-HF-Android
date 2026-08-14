//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.education.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.engagehf.modules.education.EducationNavigationEvent
import com.engagehf.modules.education.R
import com.engagehf.modules.education.videos.data.repository.EducationRepository
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.core.logging.speziLogger
import com.engagehf.modules.ui.StringResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EducationViewModel @Inject constructor(
    private val educationRepository: EducationRepository,
    private val navigator: Navigator,
) : ViewModel() {
    private val logger by speziLogger()
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadVideoSections()
    }

    private fun loadVideoSections() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            educationRepository.getVideoSections().onFailure {
                logger.e(it) { "Failed to load video sections" }
                _uiState.value =
                    UiState.Error(StringResource(R.string.education_failed_to_load_video_sections))
            }.onSuccess { videoSections ->
                _uiState.value = UiState.Success(EducationUiState(videoSections = videoSections.sortedBy { it.orderIndex }))
            }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.Retry -> loadVideoSections()

            is Action.VideoSectionClicked -> {
                navigator.navigateTo(
                    event = EducationNavigationEvent.VideoSectionClicked(
                        video = action.video
                    )
                )
            }
        }
    }
}
