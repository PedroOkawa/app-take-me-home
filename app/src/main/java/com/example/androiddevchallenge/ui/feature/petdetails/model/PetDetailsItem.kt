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
package com.example.androiddevchallenge.ui.feature.petdetails.model

import com.example.androiddevchallenge.domain.model.PetDomain
import com.example.androiddevchallenge.ui.feature.model.LocationItem
import com.example.androiddevchallenge.ui.feature.model.PetGenderItem
import com.example.androiddevchallenge.ui.feature.model.PetTypeItem
import com.example.androiddevchallenge.ui.feature.model.toUiItem

data class PetDetailsItem(
    val id: Int,
    val name: String,
    val description: String,
    val adoptionFee: Float,
    val gender: PetGenderItem,
    val image: String,
    val breed: String,
    val type: PetTypeItem,
    val birthdate: Long,
    val contactNumber: String,
    val vaccinated: Boolean,
    val location: LocationItem
)

fun PetDomain.toPetDetailsItem(): PetDetailsItem {
    return PetDetailsItem(
        id = id,
        name = name,
        description = description,
        adoptionFee = adoptionFee,
        gender = gender.toUiItem(),
        image = image,
        breed = breed,
        type = type.toUiItem(),
        birthdate = birthdate,
        contactNumber = contactNumber,
        vaccinated = vaccinated,
        location = location.toUiItem()
    )
}
