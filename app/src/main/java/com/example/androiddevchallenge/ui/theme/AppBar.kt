/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun AppBar(
    title: @Composable () -> Unit = { },
    @DrawableRes navigationIcon: Int = R.drawable.ic_pets_24,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(backgroundColor = backgroundColor),
    onNavIconPressed: () -> Unit = { }
) {
    TopAppBar(
        title = { title() },
        elevation = 0.dp,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        navigationIcon = {
            IconButton(onClick = onNavIconPressed) {
                Icon(
                    painter = painterResource(navigationIcon),
                    contentDescription = null
                )
            }
        }
    )
}
