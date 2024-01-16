package com.example.mohtdon.ui.compose.screen.following

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenFollowingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<FollowingUiState, FollowingScreenUiEffect>(FollowingUiState()),
    FollowingScreenInteraction {

    private val args: FollowingScreenArgs = FollowingScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }
}