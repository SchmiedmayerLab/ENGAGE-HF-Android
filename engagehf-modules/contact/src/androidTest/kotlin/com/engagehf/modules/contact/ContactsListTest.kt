//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.contact

import androidx.compose.ui.test.junit4.createComposeRule
import com.engagehf.modules.contact.simulator.ContactListSimulator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val contactsList = ContactsList(listOf(ContactFactory.leland, ContactFactory.mock))

    @Before
    fun init() {
        composeTestRule.setContent {
            contactsList.Content()
        }
    }

    @Test
    fun `test displays all contacts`() {
        contactsList {
            contactsList.contacts.forEach { contact ->
                assertHasContact(contact)
            }
        }
    }

    private fun contactsList(block: ContactListSimulator.() -> Unit) {
        ContactListSimulator(composeTestRule).apply(block)
    }
}
