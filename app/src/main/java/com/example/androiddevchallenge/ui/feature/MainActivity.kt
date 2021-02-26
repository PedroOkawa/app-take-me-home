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
package com.example.androiddevchallenge.ui.feature

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.feature.composable.MainNavGraph
import com.example.androiddevchallenge.ui.feature.petdetails.PetDetailsViewModel
import com.example.androiddevchallenge.ui.feature.petslist.PetsListViewModel
import com.example.androiddevchallenge.ui.theme.TakeMeHomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val petsListViewModel: PetsListViewModel by viewModels()
    private val petDetailsViewModel: PetDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeMeHomeTheme {
                MainFeature(
                    petsListViewModel,
                    petDetailsViewModel
                )
            }
        }
    }
}

// Start building your app here!
@Composable
fun MainFeature(
    petsListViewModel: PetsListViewModel = viewModel(),
    petDetailsViewModel: PetDetailsViewModel = viewModel()
) {
    MainNavGraph(
        petsListViewModel = petsListViewModel,
        petDetailsViewModel = petDetailsViewModel
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    TakeMeHomeTheme {
        MainFeature()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    TakeMeHomeTheme(darkTheme = true) {
        MainFeature()
    }
}
