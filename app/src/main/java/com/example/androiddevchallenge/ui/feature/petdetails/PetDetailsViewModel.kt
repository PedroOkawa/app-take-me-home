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
package com.example.androiddevchallenge.ui.feature.petdetails

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.model.PetDomain
import com.example.androiddevchallenge.domain.usecase.GetPetDetailsUseCase
import com.example.androiddevchallenge.ui.feature.petdetails.model.PetDetailsItem
import com.example.androiddevchallenge.ui.feature.petdetails.model.toPetDetailsItem
import com.example.androiddevchallenge.utils.SchedulerProvider
import com.example.androiddevchallenge.utils.addToDisposable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PetDetailsViewModel @Inject constructor(
    private val getPetDetailsUseCase: GetPetDetailsUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    sealed class ViewIntent {
        data class Success(val pet: PetDetailsItem) : ViewIntent()
        object Empty : ViewIntent()
        object Error : ViewIntent()
    }

    private val _state = MutableStateFlow<ViewIntent>(ViewIntent.Empty)
    val state: StateFlow<ViewIntent> = _state

    fun getPetDetails(petId: Int) {
        addToDisposable(
            getPetDetailsUseCase
                .execute(petId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    ::onGetPetDetailsSuccess,
                    ::onGetPetDetailsError
                )
        )
    }

    private fun onGetPetDetailsSuccess(petDomain: PetDomain) {
        _state.value = ViewIntent.Success(petDomain.toPetDetailsItem())
    }

    private fun onGetPetDetailsError(throwable: Throwable) {
        throwable.printStackTrace()
        _state.value = ViewIntent.Error
    }
}
