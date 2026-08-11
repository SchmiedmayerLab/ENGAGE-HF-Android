//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.account

import edu.stanford.bdh.engagehf.modules.navigation.NavigationEvent

sealed class AccountNavigationEvent : NavigationEvent {
    data class RegisterScreen(
        val email: String = "",
        val password: String = "",
    ) : AccountNavigationEvent()

    data object LoginScreen : AccountNavigationEvent()
}
