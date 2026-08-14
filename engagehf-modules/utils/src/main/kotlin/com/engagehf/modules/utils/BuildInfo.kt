//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.utils

import android.os.Build
import javax.inject.Inject

interface BuildInfo {
    fun getSdkVersion(): Int
    fun getOsVersion(): String
}

internal class BuildInfoImpl @Inject constructor() : BuildInfo {
    override fun getSdkVersion(): Int = Build.VERSION.SDK_INT
    override fun getOsVersion(): String = Build.VERSION.RELEASE
}
