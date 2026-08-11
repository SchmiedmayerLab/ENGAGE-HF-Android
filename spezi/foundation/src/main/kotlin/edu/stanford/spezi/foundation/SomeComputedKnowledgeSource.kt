//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.spezi.foundation

sealed interface ComputedKnowledgeSourceStoragePolicy {
    data object AlwaysCompute : ComputedKnowledgeSourceStoragePolicy
    data object Store : ComputedKnowledgeSourceStoragePolicy
}

interface SomeComputedKnowledgeSource<Anchor : RepositoryAnchor, Value : Any> : KnowledgeSource<Anchor, Value> {
    val storagePolicy: ComputedKnowledgeSourceStoragePolicy
}
