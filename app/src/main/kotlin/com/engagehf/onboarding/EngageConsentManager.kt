//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.onboarding

import android.content.Context
import com.engagehf.R
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.onboarding.consent.ConsentManager
import com.engagehf.modules.utils.MessageNotifier
import com.engagehf.navigation.AppNavigationEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class EngageConsentManager @Inject internal constructor(
    private val navigator: Navigator,
    private val messageNotifier: MessageNotifier,
    @ApplicationContext private val context: Context,
) : ConsentManager {

    override suspend fun getMarkdownText(): String {
        return context.getString(R.string.consent_markdown_text)
    }

    override suspend fun onConsented() {
        navigator.navigateTo(AppNavigationEvent.AppScreen(clearBackStack = true))
    }

    override suspend fun onConsentFailure(error: Throwable) {
        messageNotifier.notify(R.string.generic_error_description)
    }
}
