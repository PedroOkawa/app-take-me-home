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
package com.example.androiddevchallenge.data.model

import com.example.androiddevchallenge.domain.model.LocationDomain
import com.example.androiddevchallenge.domain.model.PetDomain
import com.example.androiddevchallenge.domain.model.PetGenderDomain
import com.example.androiddevchallenge.domain.model.PetTypeDomain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PetEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "adoption_fee")
    val adoptionFee: Float,
    @Json(name = "gender")
    val gender: PetGenderEntity,
    @Json(name = "image")
    val image: String,
    @Json(name = "breed")
    val breed: String,
    @Json(name = "type")
    val type: PetTypeEntity,
    @Json(name = "birthdate")
    val birthdate: Long,
    @Json(name = "contact_number")
    val contactNumber: String,
    @Json(name = "vaccinated")
    val vaccinated: Boolean,
    @Json(name = "location")
    val location: LocationEntity
) {
    fun toDomain(): PetDomain {
        return PetDomain(
            id = id,
            name = name,
            description = description,
            adoptionFee = adoptionFee,
            gender = gender.toDomain(),
            image = image,
            breed = breed,
            type = type.toDomain(),
            birthdate = birthdate,
            contactNumber = contactNumber,
            vaccinated = vaccinated,
            location = location.toDomain()
        )
    }
}

@JsonClass(generateAdapter = false)
enum class PetTypeEntity {
    @Json(name = "dog")
    DOG,
    @Json(name = "cat")
    CAT,
    @Json(name = "other")
    OTHER;

    fun toDomain(): PetTypeDomain {
        return when (this) {
            DOG -> PetTypeDomain.DOG
            CAT -> PetTypeDomain.CAT
            OTHER -> PetTypeDomain.OTHER
        }
    }
}

@JsonClass(generateAdapter = false)
enum class PetGenderEntity {
    @Json(name = "female")
    FEMALE,
    @Json(name = "male")
    MALE;

    fun toDomain(): PetGenderDomain {
        return when (this) {
            FEMALE -> PetGenderDomain.FEMALE
            MALE -> PetGenderDomain.MALE
        }
    }
}

@JsonClass(generateAdapter = true)
data class LocationEntity(
    @Json(name = "lat")
    val latitude: Double,
    @Json(name = "long")
    val longitude: Double
) {

    fun toDomain(): LocationDomain {
        return LocationDomain(
            latitude = latitude,
            longitude = longitude
        )
    }
}
