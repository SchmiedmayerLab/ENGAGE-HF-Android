//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.engagehf.R
import com.engagehf.navigation.screens.AccountUiState
import com.engagehf.navigation.screens.Action
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.Sizes

@Composable
fun AccountTopAppBarButton(accountUiState: AccountUiState, onAction: (Action) -> Unit) {
    IconButton(onClick = {
        onAction(Action.ShowAccountDialog(true))
    }) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = stringResource(R.string.account),
            tint = Colors.onPrimary,
            modifier = Modifier.size(Sizes.Icon.medium)
        )
    }
    AnimatedVisibility(
        visible = accountUiState.showDialog,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        AccountDialog(accountUiState = accountUiState, onAction = onAction)
    }
}
