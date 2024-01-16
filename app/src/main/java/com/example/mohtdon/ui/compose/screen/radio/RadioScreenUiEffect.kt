package com.example.mohtdon.ui.compose.screen.radio

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class RadioScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : RadioScreenUiEffect()
}