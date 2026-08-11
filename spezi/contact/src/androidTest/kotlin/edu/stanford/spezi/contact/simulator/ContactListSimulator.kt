//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.contact.simulator

import androidx.compose.ui.test.junit4.ComposeTestRule
import edu.stanford.spezi.contact.Contact
import edu.stanford.spezi.testing.ui.onNodeWithContent

class ContactListSimulator(
    private val composeTestRule: ComposeTestRule,
) {
    fun assertHasContact(contact: Contact) {
        composeTestRule.onNodeWithContent(contact.id.toString()).assertExists()
    }
}
