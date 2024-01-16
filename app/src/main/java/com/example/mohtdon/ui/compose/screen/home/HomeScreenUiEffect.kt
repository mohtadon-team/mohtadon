package com.example.mohtdon.ui.compose.screen.home

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class HomeScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : HomeScreenUiEffect()
}