//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.engagehf.R
import com.engagehf.modules.onboarding.onboarding.OnboardingView
import edu.stanford.spezi.ui.CommonScaffold

@Composable
fun OnboardingScreen() {
    CommonScaffold(title = stringResource(R.string.onboarding)) {
        OnboardingView()
    }
}
