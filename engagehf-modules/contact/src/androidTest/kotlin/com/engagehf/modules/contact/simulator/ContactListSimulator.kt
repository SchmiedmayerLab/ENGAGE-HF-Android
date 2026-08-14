//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.contact.simulator

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.engagehf.modules.contact.Contact
import com.engagehf.modules.testing.ui.onNodeWithContent

class ContactListSimulator(
    private val composeTestRule: ComposeTestRule,
) {
    fun assertHasContact(contact: Contact) {
        composeTestRule.onNodeWithContent(contact.id.toString()).assertExists()
    }
}
