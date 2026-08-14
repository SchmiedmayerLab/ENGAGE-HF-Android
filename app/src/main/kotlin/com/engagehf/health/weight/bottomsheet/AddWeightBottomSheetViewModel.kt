//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.weight.bottomsheet

import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.units.Mass
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.engagehf.R
import com.engagehf.bluetooth.component.AppScreenEvents
import com.engagehf.health.HealthRepository
import com.engagehf.health.time.TimePickerStateMapper
import com.engagehf.modules.healthconnectonfhir.Metadata
import com.engagehf.modules.utils.LocaleProvider
import com.engagehf.modules.utils.MessageNotifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddWeightBottomSheetViewModel @Inject internal constructor(
    private val appScreenEvents: AppScreenEvents,
    private val healthRepository: HealthRepository,
    private val notifier: MessageNotifier,
    private val timePickerStateMapper: TimePickerStateMapper,
    localeProvider: LocaleProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        AddWeightBottomSheetUiState(
            timePickerState = timePickerStateMapper.mapNow(),
            weightUnit = when (localeProvider.getDefaultLocale().country) {
                "US", "LR", "MM" -> WeightUnit.LBS
                else -> WeightUnit.KG
            }
        )
    )

    val uiState = _uiState.asStateFlow()

    fun onAction(action: Action) {
        when (action) {
            is Action.SaveWeight -> {
                handleSaveWeightAction()
            }

            Action.CloseSheet -> {
                appScreenEvents.emit(AppScreenEvents.Event.CloseBottomSheet)
            }

            is Action.UpdateDate -> {
                _uiState.update {
                    it.copy(
                        timePickerState = timePickerStateMapper.mapDate(
                            date = action.date,
                            timePickerState = it.timePickerState,
                        )
                    )
                }
            }

            is Action.UpdateTime -> {
                _uiState.update {
                    it.copy(
                        timePickerState = timePickerStateMapper.mapTime(
                            localTime = action.time,
                            timePickerState = it.timePickerState
                        )
                    )
                }
            }

            is Action.UpdateWeight -> {
                _uiState.update {
                    it.copy(weight = action.weight)
                }
            }
        }
    }

    private fun handleSaveWeightAction() {
        with(uiState.value) {
            WeightRecord(
                weight = when (weightUnit) {
                    WeightUnit.KG -> Mass.kilograms(weight)
                    WeightUnit.LBS -> Mass.pounds(weight)
                },
                time = timePickerStateMapper.mapInstant(timePickerState),
                zoneOffset = null,
                metadata = Metadata()
            ).let {
                viewModelScope.launch {
                    healthRepository.saveRecord(it).onFailure {
                        notifier.notify(R.string.weight_record_save_failure_message)
                    }.onSuccess {
                        appScreenEvents.emit(AppScreenEvents.Event.CloseBottomSheet)
                    }
                }
            }
        }
    }

    sealed interface Action {
        data object CloseSheet : Action
        data object SaveWeight : Action
        data class UpdateDate(val date: Instant) : Action
        data class UpdateTime(val time: LocalTime) : Action
        data class UpdateWeight(val weight: Double) : Action
    }
}
