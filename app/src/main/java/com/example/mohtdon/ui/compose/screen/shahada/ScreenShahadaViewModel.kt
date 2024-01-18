package com.example.mohtdon.ui.compose.screen.shahada

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenShahadaViewModel @Inject constructor(

) : BaseViewModel<ShahadaUiState, ShahadaScreenUiEffect>(ShahadaUiState()),
    ShahadaScreenInteraction {

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {
        sendUiEffect(ShahadaScreenUiEffect.NavigateUp)
    }
}