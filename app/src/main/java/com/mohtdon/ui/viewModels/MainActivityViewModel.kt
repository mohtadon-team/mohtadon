package com.mohtdon.ui.viewModels


import com.mohtdon.domain.usecases.GetAllReciterUseCase
import com.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllReciterUseCase: GetAllReciterUseCase
    ) : BaseViewModel() {



}