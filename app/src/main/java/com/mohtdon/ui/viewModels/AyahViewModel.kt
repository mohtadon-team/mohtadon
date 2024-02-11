package com.mohtdon.mohtdon.ui.viewModels

import com.mohtdon.domain.usecases.GetSurahAyahsUseCase
import com.mohtdon.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AyahViewModel @Inject constructor(private val getSurahAyahsUseCase: GetSurahAyahsUseCase)
    :BaseViewModel(){
}