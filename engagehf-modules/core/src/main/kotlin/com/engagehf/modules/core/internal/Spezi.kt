//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.core.internal

import com.engagehf.modules.core.ApplicationModule
import com.engagehf.modules.core.Configuration
import com.engagehf.modules.core.ConfigurationBuilder
import com.engagehf.modules.core.ConfigurationImpl
import com.engagehf.modules.core.DependenciesGraph
import com.engagehf.modules.core.Module
import com.engagehf.modules.core.SpeziApplication
import com.engagehf.modules.core.optionalDependency
import com.engagehf.modules.core.speziError
import java.util.concurrent.atomic.AtomicReference

/**
 * Singleton instance that holds the constructed [DependenciesGraph] via [Configuration] of [SpeziApplication]s. There is no direct need to
 * interact with this object, as the configuration is done via [SpeziApplicationContentProvider] on app start up time for applications that
 * conform to [SpeziApplication].
 */
@PublishedApi
internal object Spezi {
    val logger by speziCoreLogger()

    @PublishedApi
    internal val graph = AtomicReference<DependenciesGraph>(null)

    @PublishedApi
    internal fun requireGraph(): DependenciesGraph = graph.get()
        ?: run {
            val message = """
                Spezi is not configured configured yet. Please make sure your main application conforms to [SpeziApplication],
                and you did not request dependencies in the configuration block outside of module factories.
            """.trimMargin()
            speziError(message)
        }

    /**
     * Constructs the [DependenciesGraph] out of the [Configuration] of [SpeziApplication], registers [ApplicationModule] module and invokes
     * [Module.configure] on all registered modules in the graph.
     */
    fun configure(application: SpeziApplication) {
        logger.i { "Configuring spezi application $application" }
        val configuration = application.configuration as ConfigurationImpl
        val registry = configuration.registry
        registry.register(
            key = ModuleKey<ApplicationModule>(),
            factory = { ApplicationModule(application) },
        )
        val dependenciesGraph = DependenciesGraph(registry = registry)
        graph.set(dependenciesGraph)
        dependenciesGraph.configure()
    }

    /**
     * Constructs the [DependenciesGraph] out of the [Configuration] of [SpeziApplication], registers [ApplicationModule] module and invokes
     * [Module.configure] on all registered modules in the graph.
     */
    fun configure(
        scope: ConfigurationBuilder.() -> Unit,
    ) {
        val builder = ConfigurationBuilder().apply(scope)
        val applicationModule = optionalDependency<ApplicationModule>().value
        if (applicationModule != null) builder.module { applicationModule }
        val dependenciesGraph = DependenciesGraph(registry = builder.registry)
        graph.set(dependenciesGraph)
        dependenciesGraph.configure()
    }

    /**
     * Clears the singleton instance of [DependenciesGraph] and resets the configuration.
     *
     * This method is used for testing purposes only and should not be used in production code.
     */
    fun clear() {
        graph.set(null)
    }
}
