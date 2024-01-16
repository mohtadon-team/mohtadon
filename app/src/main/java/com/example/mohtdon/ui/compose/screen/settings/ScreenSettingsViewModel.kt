package com.example.mohtdon.ui.compose.screen.settings

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<SettingsUiState, SettingsScreenUiEffect>(SettingsUiState()),
    SettingsScreenInteraction {

    private val args: SettingsScreenArgs = SettingsScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }
}