//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.engagehf.modules.design.R
import com.engagehf.modules.ui.AsyncButton
import com.engagehf.modules.ui.theme.Sizes
import com.engagehf.modules.ui.theme.Spacings
import com.engagehf.modules.ui.theme.SpeziTheme
import com.engagehf.modules.ui.theme.ThemePreviews

@Composable
fun SignInWithGoogleButton(
    onButtonClick: () -> Unit,
    isLoading: Boolean = false,
) {
    AsyncButton(
        isLoading = isLoading,
        onClick = {
            onButtonClick()
        },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = stringResource(com.engagehf.modules.account.R.string.account_google_icon),
            modifier = Modifier.size(Sizes.Icon.small)
        )
        Spacer(modifier = Modifier.width(Spacings.small))
        Text(stringResource(com.engagehf.modules.account.R.string.account_sign_in_with_google))
    }
}

@ThemePreviews
@Composable
fun SignInWithGoogleButtonPreview() {
    SpeziTheme {
        SignInWithGoogleButton(onButtonClick = {})
    }
}
