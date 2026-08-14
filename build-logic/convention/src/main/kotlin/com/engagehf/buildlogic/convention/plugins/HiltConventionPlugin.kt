//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.buildlogic.convention.plugins

import dagger.hilt.android.plugin.HiltExtension
import com.engagehf.buildlogic.convention.extensions.androidTestImplementation
import com.engagehf.buildlogic.convention.extensions.apply
import com.engagehf.buildlogic.convention.extensions.extension
import com.engagehf.buildlogic.convention.extensions.findLibrary
import com.engagehf.buildlogic.convention.extensions.implementation
import com.engagehf.buildlogic.convention.model.PluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        apply(PluginId.HILT)
        apply(PluginId.KSP)

        dependencies {
            implementation(findLibrary("hilt-core"))
            add("ksp", findLibrary("hilt-compiler"))

            androidTestImplementation(findLibrary("hilt-test"))
            add("kspAndroidTest", findLibrary("hilt-test-compiler"))
        }

        extension<HiltExtension> {
            enableAggregatingTask = true
        }
    }
}
