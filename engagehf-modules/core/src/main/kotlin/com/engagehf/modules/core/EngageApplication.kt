//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core

import com.engagehf.modules.core.internal.Engage

/**
 * Base interface that all Engage applications must implement to provide the Engage modules dependency graph
 */
interface EngageApplication {

    /**
     * The [Configuration] of the [EngageApplication] that contains all registered modules.
     */
    val configuration: Configuration

    companion object {
        /**
         * Constructs the [DependenciesGraph] out of the [Configuration] of [EngageApplication],
         * registers [ApplicationModule] module and invokes [Module.configure] on all registered modules in the graph.
         *
         * Note that there is no need to call this method directly, as it is invoked automatically on app start up time by Engage Framework,
         * This method can be used to rebuild the dependency graph in case of a configuration change.
         *
         * @param application the [EngageApplication] instance to configure
         */
        fun configure(application: EngageApplication) {
            Engage.configure(application = application)
        }

        /**
         * Constructs the [DependenciesGraph] out of the [Configuration] of [EngageApplication],
         * registers [ApplicationModule] module and invokes [Module.configure] on all registered modules in the graph.
         *
         * Note that there is no need to call this method directly, as it is invoked automatically on app start up time by Engage Framework,
         * This method can be used to rebuild the dependency graph in case of a configuration change.
         *
         * @param scope the configuration block to configure the [DependenciesGraph]
         */
        fun configure(
            scope: ConfigurationBuilder.() -> Unit,
        ) {
            Engage.configure(scope = scope)
        }

        /**
         * Clears the [DependenciesGraph] and all registered modules.
         *
         * Note that there is no need to call this method directly, as it is invoked automatically on app start up time by Engage Framework,
         * This method can be used to clear the dependency graph in case of a configuration change.
         */
        fun clear() {
            Engage.clear()
        }
    }
}
