package com.example.mohtdon.ui.compose.screen.splash

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class SplashScreenUiEffect() : BaseUiEffect {
    data object NavigateToHomeScreen : SplashScreenUiEffect()
}