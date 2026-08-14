//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.notification.setting

import javax.inject.Inject

/**
 * Maps [NotificationSettingViewModel.Action] to [NotificationSettingViewModel.UiState].
 */
internal class NotificationSettingsStateMapper @Inject constructor() {
    fun mapSwitchChanged(
        action: NotificationSettingViewModel.Action.SwitchChanged,
        currentSettings: NotificationSettings,
    ): NotificationSettings {
        return currentSettings.update(action.notificationType, action.isChecked)
    }
}
