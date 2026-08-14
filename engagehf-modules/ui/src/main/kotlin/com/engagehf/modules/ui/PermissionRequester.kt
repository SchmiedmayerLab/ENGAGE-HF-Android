//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun PermissionRequester(
    missingPermissions: Set<String>,
    onResult: (Boolean, String) -> Unit,
) {
    val permission = missingPermissions.firstOrNull() ?: return
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted -> onResult(granted, permission) }

    LaunchedEffect(key1 = permission) {
        launcher.launch(permission)
    }
}
