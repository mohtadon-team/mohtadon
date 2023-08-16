package com.example.straterproject.ui.viewModels

import com.example.domain.usecases.GetAllSurahUseCase
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(private val getAllSurahUseCase: GetAllSurahUseCase)
    : BaseViewModel() {
}