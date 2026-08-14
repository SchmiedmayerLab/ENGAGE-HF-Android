//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.buildlogic.convention.plugins

import com.engagehf.buildlogic.convention.extensions.android
import com.engagehf.buildlogic.convention.extensions.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DesugaringConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        android {
            compileOptions {
                isCoreLibraryDesugaringEnabled = true
            }
        }

        dependencies {
            add("coreLibraryDesugaring", findLibrary("android-desugaring"))
        }
    }
}
