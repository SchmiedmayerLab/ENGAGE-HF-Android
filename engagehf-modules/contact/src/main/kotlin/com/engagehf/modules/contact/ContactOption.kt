//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.contact

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.engagehf.modules.core.logging.engageLogger
import com.engagehf.modules.foundation.UUID
import com.engagehf.modules.ui.ComposableContent
import com.engagehf.modules.ui.DefaultElevatedCard
import com.engagehf.modules.ui.StringResource
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.Spacings
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews
import java.util.UUID

/**
 * ContactOption data class used to represent a contact option.
 *
 * @param id the unique identifier of the contact option
 * @param image the image of the contact option
 * @param title the title of the contact option
 * @param action the action of the contact option
 */
data class ContactOption(
    val id: UUID = UUID(),
    val image: ImageVector?,
    val title: StringResource,
    val action: (Context) -> Unit,
) : ComposableContent {

    @Composable
    override fun Content(modifier: Modifier) {
        val context = LocalContext.current
        DefaultElevatedCard(
            modifier = modifier
                .defaultMinSize(80.dp)
                .fillMaxWidth()
                .clickable {
                    action(context)
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(Spacings.small)
                    .fillMaxWidth()
            ) {
                Icon(
                    image ?: Icons.Default.Email,
                    contentDescription = title.text(),
                    tint = Colors.primary,
                )
                Text(
                    text = title.text(),
                    maxLines = 1,
                )
            }
        }
    }

    companion object {
        internal val logger by engageLogger()
    }
}

@Composable
@ThemePreviews
private fun ContactOptionCardPreview() {
    EngageTheme {
        val option = ContactOption.text(
            number = "+1 (650) 723-2300"
        )
        option.Content()
    }
}
