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
package com.example.androiddevchallenge.domain.model

data class PetDomain(
    val id: String,
    val name: String,
    val description: String,
    val adoptionFee: Float,
    val gender: PetGenderDomain,
    val image: String,
    val breed: String,
    val type: PetTypeDomain,
    val birthdate: Long,
    val contactNumber: String,
    val vaccinated: Boolean,
    val location: LocationDomain
)

enum class PetGenderDomain {
    FEMALE,
    MALE
}

enum class PetTypeDomain {
    DOG,
    CAT,
    OTHER
}

data class LocationDomain(
    val latitude: Double,
    val longitude: Double
)
