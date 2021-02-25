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
package com.example.androiddevchallenge.ui.feature

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.model.PetDomain
import com.example.androiddevchallenge.domain.usecase.GetPetsUseCase
import com.example.androiddevchallenge.utils.SchedulerProvider
import com.example.androiddevchallenge.utils.addToDisposable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPetsUseCase: GetPetsUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val _state = MutableStateFlow<List<PetDomain>>(emptyList())
    val state: StateFlow<List<PetDomain>>
        get() = _state

    init {
        addToDisposable(
            getPetsUseCase
                .execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    ::onGetPetsSuccess,
                    ::onGetPetsError
                )
        )
    }

    private fun onGetPetsSuccess(petsDomain: List<PetDomain>) {
        _state.value = petsDomain
    }

    private fun onGetPetsError(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
