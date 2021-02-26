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
package com.example.androiddevchallenge.ui.feature.petdetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.feature.model.PetGenderItem
import com.example.androiddevchallenge.ui.feature.petdetails.PetDetailsViewModel
import com.example.androiddevchallenge.ui.feature.petdetails.PetDetailsViewModel.ViewIntent
import com.example.androiddevchallenge.ui.feature.petdetails.model.PetDetailsItem
import com.example.androiddevchallenge.ui.theme.AppBar
import dev.chrisbanes.accompanist.glide.GlideImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun PetDetails(
    petId: Int,
    viewModel: PetDetailsViewModel,
    navController: NavController
) {
    viewModel.getPetDetails(petId)

    Box {
        val viewIntentState by viewModel.state.collectAsState()

        when (viewIntentState) {
            is ViewIntent.Empty -> PetDetailsEmpty()
            is ViewIntent.Error -> PetDetailsError()
            is ViewIntent.Success -> PetDetailsSuccess(
                petDetails = (viewIntentState as ViewIntent.Success).pet
            )
        }

        AppBar(
            navigationIcon = R.drawable.ic_arrow_back_24,
            onNavIconPressed = { navController.navigateUp() },
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun PetDetailsEmpty() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.Center),
        )
    }
}

@Composable
fun PetDetailsError() {
}

@Composable
fun PetDetailsSuccess(petDetails: PetDetailsItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.secondary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            GlideImage(
                data = petDetails.image,
                contentDescription = null,
                requestBuilder = {
                    val requestOptions = RequestOptions().centerCrop()
                    apply(requestOptions)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(384.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(MaterialTheme.colors.primaryVariant, Color.Transparent)
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, MaterialTheme.colors.secondary)
                        )
                    )
            )
        }
        val genderRes = when (petDetails.gender) {
            PetGenderItem.FEMALE -> R.string.pets_list_gender_female
            PetGenderItem.MALE -> R.string.pets_list_gender_male
        }
        Text(
            text = petDetails.name,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSecondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp
                )
        )
        Text(
            text = stringResource(id = genderRes),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = petDetails.breed,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "\"${petDetails.description}\"",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 16.dp
            )
        )
        Row(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 16.dp
            )
        ) {
            Text(
                text = stringResource(R.string.pets_details_vaccinated),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f)
            )
            val vaccinatedRes = when (petDetails.vaccinated) {
                true -> R.string.yes
                false -> R.string.no
            }
            Text(
                text = stringResource(vaccinatedRes),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.End
            )
        }
        Row(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 16.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.pets_details_adoption_fee),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f)
            )
            Text(
                text = "${petDetails.adoptionFee} $",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.End
            )
        }
        Row(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 16.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.pets_details_birthdate),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f)
            )
            val birthdate = Date(petDetails.birthdate)
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            Text(
                text = formatter.format(birthdate),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.End
            )
        }
        Row(
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.pets_details_contact_number),
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f)
            )
            Text(
                text = petDetails.contactNumber,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.End
            )
        }
    }
}
