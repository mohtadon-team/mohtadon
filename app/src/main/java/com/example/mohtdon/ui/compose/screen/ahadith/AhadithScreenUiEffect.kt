package com.example.mohtdon.ui.compose.screen.ahadith

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class AhadithScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : AhadithScreenUiEffect()

}