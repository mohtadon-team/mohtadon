package com.example.mohtdon.ui.compose.screen.splash

import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenSplashViewModel @Inject constructor(

):
    BaseViewModel<SplashUiState, SplashScreenUiEffect>(SplashUiState()), SplashScreenInteraction {

    override fun getData() {

    }

    override fun onClickStartNow() {
        sendUiEffect(SplashScreenUiEffect.NavigateToHomeScreen)
    }
}