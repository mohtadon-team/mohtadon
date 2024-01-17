package com.example.mohtdon.ui.compose.screen.moshaf

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenMoshafViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<MoshafUiState, MoshafScreenUiEffect>(MoshafUiState()), MoshafScreenInteraction {

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }
}