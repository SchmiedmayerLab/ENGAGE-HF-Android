//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.contact

import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import com.engagehf.modules.ui.StringResource

fun ContactOption.Companion.website(uriString: String): ContactOption =
    ContactOption(
        image = Icons.Default.Info,
        title = StringResource(R.string.contact_website),
        action = { context ->
            runCatching {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
                context.startActivity(browserIntent)
            }.onFailure {
                logger.e(it) { "Failed to open intent for website at `$uriString`." }
            }
        }
    )
