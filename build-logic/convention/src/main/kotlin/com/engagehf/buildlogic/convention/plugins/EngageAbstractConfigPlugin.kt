//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.buildlogic.convention.plugins

import com.engagehf.buildlogic.convention.extensions.android
import com.engagehf.buildlogic.convention.extensions.androidTestImplementation
import com.engagehf.buildlogic.convention.extensions.apply
import com.engagehf.buildlogic.convention.extensions.implementation
import com.engagehf.buildlogic.convention.extensions.testImplementation
import com.engagehf.buildlogic.convention.model.PluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

abstract class EngageAbstractConfigPlugin(private val modulePlugin: PluginId) : Plugin<Project> {
    private val defaultConfig by lazy { EngageBaseConfigConventionPlugin() }

    override fun apply(project: Project) = with(project) {
        apply(modulePlugin)
        apply(PluginId.JETBRAINS_KOTLIN_ANDROID)

        defaultConfig.apply(this)

        android {
            defaultConfig {
                testInstrumentationRunner = "com.engagehf.modules.testing.ui.HiltApplicationTestRunner"
            }
        }

        dependencies {
            implementation(project(":engagehf-modules:utils"))

            testImplementation(project(":engagehf-modules:testing"))

            androidTestImplementation(project(":engagehf-modules:testing"))
        }
    }
}
