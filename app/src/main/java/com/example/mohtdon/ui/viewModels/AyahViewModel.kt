package com.example.mohtdon.ui.viewModels

import com.example.domain.usecases.GetSurahAyahsUseCase
import com.example.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AyahViewModel @Inject constructor(private val getSurahAyahsUseCase: GetSurahAyahsUseCase)
    :BaseViewModel(){
}