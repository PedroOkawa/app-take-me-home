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
package com.example.androiddevchallenge.domain.usecase

import com.example.androiddevchallenge.domain.model.PetDomain
import com.example.androiddevchallenge.domain.repository.PetRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPetsUseCase @Inject constructor(
    private val petRepository: PetRepository
) {

    fun execute(): Single<List<PetDomain>> {
        return petRepository
            .getPets()
            .map { petEntities ->
                petEntities.map { petEntity ->
                    petEntity.toDomain()
                }
            }
    }
}
