//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.notification.setting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NotificationSettingsStateMapperTest {

    private val notificationSettingsStateMapper: NotificationSettingsStateMapper =
        NotificationSettingsStateMapper()

    @Test
    fun `mapSwitchChanged should update notification settings`() {
        val currentSettings = NotificationSettings(
            mapOf(NotificationType.APPOINTMENT_REMINDERS to true),
        )
        val action = NotificationSettingViewModel.Action.SwitchChanged(
            NotificationType.APPOINTMENT_REMINDERS,
            false
        )
        val updatedSettings =
            notificationSettingsStateMapper.mapSwitchChanged(action, currentSettings)
        assertThat(updatedSettings).isEqualTo(
            NotificationSettings(
                mapOf(NotificationType.APPOINTMENT_REMINDERS to false),
            )
        )
    }
}
