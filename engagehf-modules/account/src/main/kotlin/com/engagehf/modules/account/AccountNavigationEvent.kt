//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account

import com.engagehf.modules.navigation.NavigationEvent

sealed class AccountNavigationEvent : NavigationEvent {
    data class RegisterScreen(
        val email: String = "",
        val password: String = "",
    ) : AccountNavigationEvent()

    data object LoginScreen : AccountNavigationEvent()
}
