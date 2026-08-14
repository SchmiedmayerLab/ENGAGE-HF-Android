//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.account.manager

interface InvitationAuthManager {
    suspend fun checkInvitationCode(invitationCode: String): Result<Unit>
}
