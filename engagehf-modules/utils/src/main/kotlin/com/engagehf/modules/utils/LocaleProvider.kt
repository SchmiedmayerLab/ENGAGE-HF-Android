//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.utils

import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

interface LocaleProvider {
    fun getDefaultLocale(): Locale
}

@Singleton
internal class LocaleProviderImpl @Inject constructor() : LocaleProvider {
    override fun getDefaultLocale(): Locale = Locale.getDefault()
}
