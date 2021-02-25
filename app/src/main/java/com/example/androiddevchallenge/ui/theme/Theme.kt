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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = blueGrey900,
    primaryVariant = blueGrey800,
    secondary = teal200,
    background = blueGrey1000,
    surface = teal300,
    onPrimary = blueGrey50,
    onSecondary = teal900,
    onBackground = blueGrey50,
    onSurface = teal900
)

private val LightColorPalette = lightColors(
    primary = indigo400,
    primaryVariant = indigo500,
    secondary = deepPurple100,
    secondaryVariant = deepPurple200,
    background = indigo50,
    surface = indigo50,
    onPrimary = indigo50,
    onSecondary = deepPurple900,
    onBackground = deepPurple900,
    onSurface = deepPurple900
)

@Composable
fun TakeMeHomeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
