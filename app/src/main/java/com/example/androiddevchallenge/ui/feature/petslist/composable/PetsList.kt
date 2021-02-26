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
package com.example.androiddevchallenge.ui.feature.petslist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.feature.composable.MainDestinations
import com.example.androiddevchallenge.ui.feature.model.PetGenderItem
import com.example.androiddevchallenge.ui.feature.model.PetTypeItem
import com.example.androiddevchallenge.ui.feature.petslist.PetsListViewModel
import com.example.androiddevchallenge.ui.feature.petslist.PetsListViewModel.ViewIntent
import com.example.androiddevchallenge.ui.feature.petslist.model.PetListItem
import com.example.androiddevchallenge.ui.theme.AppBar
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PetsList(
    viewModel: PetsListViewModel,
    navController: NavController
) {
    viewModel.getPetsList()

    Scaffold(
        topBar = {
            AppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        },
        content = {
            val viewIntentState by viewModel.state.collectAsState()

            when (viewIntentState) {
                is ViewIntent.Empty -> PetsListEmpty()
                is ViewIntent.Error -> PetsListError()
                is ViewIntent.Success -> PetsListSuccess(
                    petsList = (viewIntentState as ViewIntent.Success).pets,
                    onPetClicked = { petId ->
                        navController.navigate("${MainDestinations.PET_DETAILS}/$petId")
                    }
                )
            }
        }
    )
}

@Composable
fun PetsListEmpty() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.Center),
        )
    }
}

@Composable
fun PetsListError() {
}

@Composable
fun PetsListSuccess(
    petsList: List<PetListItem>,
    onPetClicked: (petId: Int) -> Unit
) {
    LazyColumn {
        items(petsList) { item ->
            PetItem(
                petListItem = item,
                onPetClicked = onPetClicked
            )
        }
    }
}

@Composable
fun PetItem(
    petListItem: PetListItem,
    onPetClicked: (petId: Int) -> Unit

) {
    Card(
        modifier = Modifier
            .clickable(onClick = { onPetClicked(petListItem.id) })
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant)
                    .height(192.dp)
                    .weight(0.4f)
                    .padding(16.dp)
            ) {
                Text(
                    text = petListItem.name,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = MaterialTheme.colors.onPrimary
                )
                val genderRes = when (petListItem.gender) {
                    PetGenderItem.FEMALE -> R.string.pets_list_gender_female
                    PetGenderItem.MALE -> R.string.pets_list_gender_male
                }
                Text(
                    text = stringResource(id = genderRes),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.weight(0.3f))
                val typeRes = when (petListItem.type) {
                    PetTypeItem.DOG -> R.string.pets_list_type_dog
                    PetTypeItem.CAT -> R.string.pets_list_type_cat
                    PetTypeItem.OTHER -> R.string.pets_list_type_other
                }
                Text(
                    text = stringResource(id = typeRes),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .wrapContentHeight()

            ) {
                GlideImage(
                    data = petListItem.image,
                    contentDescription = null,
                    requestBuilder = {
                        val requestOptions = RequestOptions().centerCrop()
                        apply(requestOptions)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(192.dp)
                        .align(Alignment.CenterEnd)
                )

                Box(
                    modifier = Modifier
                        .width(128.dp)
                        .height(192.dp)
                        .align(Alignment.CenterStart)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(MaterialTheme.colors.primaryVariant, Color.Transparent)
                            )
                        )
                )
            }
        }
    }
}
