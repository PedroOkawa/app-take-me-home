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
package com.example.androiddevchallenge.ui.feature.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.feature.petdetails.PetDetailsViewModel
import com.example.androiddevchallenge.ui.feature.petdetails.composable.PetDetails
import com.example.androiddevchallenge.ui.feature.petslist.PetsListViewModel
import com.example.androiddevchallenge.ui.feature.petslist.composable.PetsList

object MainDestinations {
    const val PETS_LIST = "petslist"
    const val PET_DETAILS = "petdetails"
}

object MainArguments {
    const val PET_ID = "petId"
}

@Composable
fun MainNavGraph(
    petsListViewModel: PetsListViewModel,
    petDetailsViewModel: PetDetailsViewModel,
    startDestination: String = MainDestinations.PETS_LIST
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = MainDestinations.PETS_LIST) {
            PetsList(
                viewModel = petsListViewModel,
                navController = navController
            )
        }
        composable(
            route = "${MainDestinations.PET_DETAILS}/{${MainArguments.PET_ID}}",
            arguments = listOf(navArgument(MainArguments.PET_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            PetDetails(
                petId = arguments.getInt(MainArguments.PET_ID),
                viewModel = petDetailsViewModel,
                navController = navController
            )
        }
    }
}
