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
import com.engagehf.buildlogic.convention.extensions.debugImplementation
import com.engagehf.buildlogic.convention.extensions.findBundle
import com.engagehf.buildlogic.convention.extensions.findLibrary
import com.engagehf.buildlogic.convention.extensions.implementation
import com.engagehf.buildlogic.convention.model.PluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class EngageComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        apply(PluginId.COMPOSE_COMPILER)

        android {
            buildFeatures {
                compose = true
            }

            dependencies {
                val composeBom = platform(findLibrary("compose-bom"))
                implementation(composeBom)
                implementation(findBundle("compose"))

                androidTestImplementation(composeBom)
                androidTestImplementation(findBundle("unit-testing"))
                androidTestImplementation(findBundle("compose-androidTest"))
                debugImplementation(findLibrary("compose-ui-tooling"))
                debugImplementation(findLibrary("compose-ui-test-manifest"))
            }
        }
    }
}
