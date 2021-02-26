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
package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.model.PetEntity
import com.example.androiddevchallenge.domain.repository.PetRepository
import com.example.androiddevchallenge.utils.AssetsHelper
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

private val LOCAL_PETS_JSON_FILE_NAME = "local_pets.json"
private val NULL_POINTER_JSON_MESSAGE = "The json file name is invalid!"
private val NO_SUCH_ELEMENT_MESSAGE = "The pet you requested doesn't exist!"

@Singleton
class LocalPetRepository @Inject constructor(
    private val assetsHelper: AssetsHelper,
    private val moshi: Moshi
) : PetRepository {

    override fun getPets(): Single<List<PetEntity>> {
        return Single.fromCallable { readPetsJson() }
    }

    override fun getPet(id: Int): Single<PetEntity> {
        return Single.fromCallable {
            readPetsJson()
                .firstOrNull { petEntity ->
                    petEntity.id == id
                } ?: throw NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE)
        }
    }

    private fun readPetsJson(): List<PetEntity> {
        val jsonBody = assetsHelper
            .readJsonFromAssets(LOCAL_PETS_JSON_FILE_NAME) ?: throw NullPointerException(NULL_POINTER_JSON_MESSAGE)
        val type = Types.newParameterizedType(List::class.java, PetEntity::class.java)
        val adapter = moshi.adapter<List<PetEntity>>(type)
        return adapter.fromJson(jsonBody).orEmpty()
    }
}
