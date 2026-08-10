//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.navigation

/**
 * Represents an event that triggers a navigation action.
 */
interface NavigationEvent {
    /**
     * Attempts to navigate to the previous screen in the back stack.
     */
    data object PopBackStack : NavigationEvent

    /**
     * Attempts to navigate up in the navigation hierarchy.
     */
    data object NavigateUp : NavigationEvent
}
