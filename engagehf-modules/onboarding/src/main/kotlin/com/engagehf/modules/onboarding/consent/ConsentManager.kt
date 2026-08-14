//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.onboarding.consent

/**
 * A interface that needs to be implemented and provided by the app to provide the consent text and handle consent actions.
 * @see com.engagehf.onboarding.EngageConsentManager
 */
interface ConsentManager {
    suspend fun getMarkdownText(): String
    suspend fun onConsented()
    suspend fun onConsentFailure(error: Throwable)
}
