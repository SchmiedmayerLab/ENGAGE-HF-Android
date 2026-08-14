//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui.personalinfo.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.engagehf.modules.ui.personalinfo.NameFieldRow
import com.engagehf.modules.ui.personalinfo.OutlinedNameFieldRow
import com.engagehf.modules.ui.personalinfo.PersonNameComponents

@Composable
fun NameFieldsTestComposable(nameBuilder: PersonNameComponents.Builder) {
    Column {
        NameFieldRow(
            builder = nameBuilder,
            property = PersonNameComponents.Builder::givenName,
            description = {
                Text("First Name")
            },
            placeholder = {
                Text("enter your first name")
            },
        )

        HorizontalDivider()

        OutlinedNameFieldRow(
            builder = nameBuilder,
            property = PersonNameComponents.Builder::familyName,
            description = {
                Text("Last Name")
            },
            placeholder = {
                Text("enter your last name")
            },
        )
    }
}
