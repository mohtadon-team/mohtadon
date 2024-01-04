package com.example.mohtdon.ui.viewModels


import com.example.domain.usecases.GetAllReciterUseCase
import com.example.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllReciterUseCase: GetAllReciterUseCase
    ) :BaseViewModel() {



}