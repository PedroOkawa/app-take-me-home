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
package com.example.androiddevchallenge.ui.feature.model

import com.example.androiddevchallenge.domain.model.LocationDomain
import com.example.androiddevchallenge.domain.model.PetGenderDomain
import com.example.androiddevchallenge.domain.model.PetTypeDomain

enum class PetGenderItem {
    FEMALE,
    MALE
}

fun PetGenderDomain.toUiItem(): PetGenderItem {
    return when (this) {
        PetGenderDomain.FEMALE -> PetGenderItem.FEMALE
        PetGenderDomain.MALE -> PetGenderItem.MALE
    }
}

enum class PetTypeItem {
    DOG,
    CAT,
    OTHER
}

fun PetTypeDomain.toUiItem(): PetTypeItem {
    return when (this) {
        PetTypeDomain.DOG -> PetTypeItem.DOG
        PetTypeDomain.CAT -> PetTypeItem.CAT
        PetTypeDomain.OTHER -> PetTypeItem.OTHER
    }
}

data class LocationItem(
    val latitude: Double,
    val longitude: Double
)

fun LocationDomain.toUiItem(): LocationItem {
    return LocationItem(
        latitude = latitude,
        longitude = longitude
    )
}
