//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.engagehf.R
import com.engagehf.modules.ui.VerticalSpacer
import com.engagehf.modules.ui.theme.TextStyles

@Composable
fun AddDataHeader(
    onClose: () -> Unit,
    onSave: () -> Unit,
) {
    Row {
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(R.string.close_dialog_icon)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onSave) {
            Text(text = stringResource(R.string.save))
        }
    }
    VerticalSpacer()
    Text(text = stringResource(R.string.add_data), style = TextStyles.headlineLarge)
    VerticalSpacer()
    HorizontalDivider()
}
