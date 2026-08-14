//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.contact

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.engagehf.modules.ui.ComposableContent
import com.engagehf.modules.ui.testIdentifier

data class ContactsList(
    val contacts: List<Contact>,
) : ComposableContent {
    @Composable
    override fun Content(modifier: Modifier) {
        LazyColumn(modifier = modifier) {
            items(contacts) {
                it.Content(
                    modifier = Modifier.testIdentifier(
                        ContactsListTestIdentifier.CONTACT,
                        suffix = it.id.toString()
                    )
                )
            }
        }
    }
}

enum class ContactsListTestIdentifier {
    CONTACT,
}
