//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.buildlogic.convention.plugins

import com.engagehf.buildlogic.convention.extensions.apply
import com.engagehf.buildlogic.convention.extensions.findLibrary
import com.engagehf.buildlogic.convention.extensions.implementation
import com.engagehf.buildlogic.convention.model.PluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class EngageSerializationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        apply(PluginId.SERIALIZATION)

        dependencies {
            implementation(findLibrary("kotlinx-serialization-json"))
        }
    }
}
