package com.example.mohtdon.ui.compose.screen.shahada

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class ShahadaScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : ShahadaScreenUiEffect()
}