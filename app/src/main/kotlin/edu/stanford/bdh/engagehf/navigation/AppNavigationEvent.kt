//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.navigation

import edu.stanford.bdh.engagehf.modules.navigation.NavigationEvent

sealed interface AppNavigationEvent : NavigationEvent {
    data class AppScreen(val clearBackStack: Boolean) : AppNavigationEvent
    data class QuestionnaireScreen(val questionnaireId: String) : AppNavigationEvent
    data object ContactScreen : AppNavigationEvent
}
