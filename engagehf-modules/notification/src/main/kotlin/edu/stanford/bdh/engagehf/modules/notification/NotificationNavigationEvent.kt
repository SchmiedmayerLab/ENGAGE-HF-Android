//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.notification

import edu.stanford.bdh.engagehf.modules.navigation.NavigationEvent

sealed class NotificationNavigationEvent : NavigationEvent {

    data object NotificationSettings : NotificationNavigationEvent()
}
