package com.example.mohtdon.ui.compose.screen.settings

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class SettingsScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : SettingsScreenUiEffect()
}