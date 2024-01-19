package com.example.mohtdon.ui.compose.screen.azkar

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class AzkarScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : AzkarScreenUiEffect()
}