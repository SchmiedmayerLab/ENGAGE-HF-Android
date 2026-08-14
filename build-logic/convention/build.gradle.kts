//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.engagehf.buildlogic"

val javaVersion = JavaVersion.VERSION_17

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.hilt.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

fun NamedDomainObjectContainer<PluginDeclaration>.conventionPlugin(id: String, className: String) {
    register(className) {
        this.id = "engagehf.$id"
        implementationClass = "com.engagehf.buildlogic.convention.plugins.$className"
    }
}

gradlePlugin {
    plugins {
        // Please keep plugins sorted. Select all method calls below and in Android Studio `Edit > Sort Lines`
        conventionPlugin(id = "application", className = "EngageApplicationConventionPlugin")
        conventionPlugin(id = "base", className = "EngageBaseConfigConventionPlugin")
        conventionPlugin(id = "compose", className = "EngageComposeConventionPlugin")
        conventionPlugin(id = "desugaring", className = "DesugaringConventionPlugin")
        conventionPlugin(id = "hilt", className = "HiltConventionPlugin")
        conventionPlugin(id = "library", className = "EngageLibraryConventionPlugin")
        conventionPlugin(id = "serialization", className = "EngageSerializationConventionPlugin")
    }
}
