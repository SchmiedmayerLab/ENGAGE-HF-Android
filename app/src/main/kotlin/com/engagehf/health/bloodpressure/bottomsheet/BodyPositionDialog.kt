//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.health.bloodpressure.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.engagehf.R
import com.engagehf.health.components.ItemsDialog
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun BodyPositionsDialog(
    onDismissRequest: () -> Unit,
    onOptionSelected: (BodyPositions) -> Unit,
    bodyPositions: List<BodyPositions>,
) {
    val items = bodyPositions.map {
        when (it) {
            BodyPositions.BODY_POSITION_UNKNOWN -> stringResource(R.string.not_set)
            BodyPositions.BODY_POSITION_STANDING_UP -> stringResource(R.string.standing_up)
            BodyPositions.BODY_POSITION_SITTING_DOWN -> stringResource(R.string.sitting_down)
            BodyPositions.BODY_POSITION_LYING_DOWN -> stringResource(R.string.lying_down)
            BodyPositions.BODY_POSITION_RECLINING -> stringResource(R.string.reclining)
        }
    }
    ItemsDialog(
        title = stringResource(id = R.string.body_position),
        items = items,
        onDismissRequest = onDismissRequest,
        onOptionSelected = { onOptionSelected(bodyPositions[it]) }
    )
}

@ThemePreviews
@Composable
fun BodyPositionsDialogPreview() {
    EngageTheme {
        BodyPositionsDialog(
            onDismissRequest = {},
            onOptionSelected = {},
            bodyPositions = BodyPositions.entries
        )
    }
}
