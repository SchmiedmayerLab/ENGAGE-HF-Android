//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package edu.stanford.bdh.engagehf.modules.education

import edu.stanford.bdh.engagehf.modules.education.videos.Video
import kotlinx.serialization.Serializable

@Serializable
sealed class EducationRoutes {

    @Serializable
    data class VideoDetail(val video: Video) : EducationRoutes()
}
