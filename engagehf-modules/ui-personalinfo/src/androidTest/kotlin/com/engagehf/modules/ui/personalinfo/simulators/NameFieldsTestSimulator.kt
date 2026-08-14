//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui.personalinfo.simulators

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.engagehf.modules.testing.ui.onNodeWithIdentifier
import com.engagehf.modules.ui.personalinfo.NameTextFieldTestIdentifier
import com.engagehf.modules.ui.personalinfo.PersonNameComponents
import kotlin.reflect.KMutableProperty1

class NameFieldsTestSimulator(
    private val composeTestRule: ComposeTestRule,
) {
    fun assertTextExists(text: String) {
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }

    fun enterText(property: KMutableProperty1<PersonNameComponents.Builder, String?>, text: String) {
        composeTestRule
            .onNodeWithIdentifier(NameTextFieldTestIdentifier.TEXT_FIELD, property.name)
            .performTextInput(text)
    }
}
