//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf.modules.ui.personalinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.engagehf.modules.ui.AsyncImageResource
import com.engagehf.modules.ui.ImageResource
import com.engagehf.modules.ui.StringResource
import com.engagehf.modules.ui.lighten
import com.engagehf.modules.ui.theme.Colors
import com.engagehf.modules.ui.theme.EngageTheme
import com.engagehf.modules.ui.theme.ThemePreviews
import kotlin.math.min

@Composable
fun UserProfile(
    name: PersonNameComponents,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier
            .onSizeChanged { size = it }
            .aspectRatio(1f)
    ) {
        val sideLength = min(size.height, size.width).dp
        Box(
            modifier = Modifier
                .size(sideLength)
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImageResource(
                modifier = Modifier.fillMaxSize(),
                url = imageUrl,
                loading = {
                    name.Content(sideLength = sideLength)
                },
                error = {
                    name.Content(sideLength = sideLength)
                }
            )
        }
    }
}

@Composable
private fun PersonNameComponents.Content(sideLength: Dp) {
    val formattedName = remember(this) {
        formatted(PersonNameComponents.FormatStyle.ABBREVIATED)
    }
    Box(
        modifier = Modifier
            .background(Colors.secondary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formattedName,
            fontSize = (sideLength.value * 0.2).sp,
            color = Colors.secondary.lighten(),
        )
    }
}

private typealias UserProfilePreviewData = Pair<PersonNameComponents, suspend () -> ImageResource?>

private class UserProfileProvider : PreviewParameterProvider<UserProfilePreviewData> {
    override val values: Sequence<UserProfilePreviewData> = sequenceOf(
        Pair(
            PersonNameComponents(
                givenName = "Paul",
                familyName = "Schmiedmayer",
            )
        ) { null },
        Pair(
            PersonNameComponents(
                namePrefix = "Prof.",
                givenName = "Oliver",
                middleName = "Oppers",
                familyName = "Aalami"
            )
        ) { null },
        Pair(
            PersonNameComponents(
                givenName = "Vishnu",
                familyName = "Ravi",
            )
        ) {
            ImageResource.Vector(
                Icons.Default.Person,
                StringResource("Person")
            )
        },
    )
}

@ThemePreviews
@Composable
private fun UserProfilePreview(
    @PreviewParameter(UserProfileProvider::class) profileData: UserProfilePreviewData,
) {
    EngageTheme {
        UserProfile(
            name = profileData.first,
            imageUrl = null,
        )
    }
}
