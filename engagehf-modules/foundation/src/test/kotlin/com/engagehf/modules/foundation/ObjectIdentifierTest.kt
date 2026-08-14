//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.foundation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ObjectIdentifierTest {
    private data class TestClass(val name: String)

    @Test
    fun `it should handle same object equality correctly`() {
        // given
        val firstInstance = TestClass("Engage")
        val secondInstance = TestClass("Engage")
        val apodiniInstance = TestClass("Apodini")
        val otherApodiniInstance = TestClass("Apodini")
        val identifier = ObjectIdentifier(firstInstance)
        val otherIdentifier = ObjectIdentifier(secondInstance)
        val apodini = ObjectIdentifier(apodiniInstance)
        val otherApodini = ObjectIdentifier(otherApodiniInstance)

        // then
        assertThat(firstInstance).isEqualTo(secondInstance)
        assertThat(firstInstance).isEqualTo(identifier.ref)
        assertThat(apodiniInstance).isEqualTo(otherApodiniInstance)
        assertThat(apodiniInstance).isEqualTo(apodini.ref)
        assertThat(identifier).isEqualTo(ObjectIdentifier(firstInstance))
        assertThat(apodini).isEqualTo(ObjectIdentifier(apodiniInstance))
        assertThat(identifier).isNotEqualTo(apodini)
        assertThat(identifier).isNotEqualTo(otherIdentifier)
        assertThat(apodini).isNotEqualTo(otherApodini)
    }
}
