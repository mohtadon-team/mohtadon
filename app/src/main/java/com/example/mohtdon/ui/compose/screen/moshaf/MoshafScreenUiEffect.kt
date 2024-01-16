package com.example.mohtdon.ui.compose.screen.moshaf

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class MoshafScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : MoshafScreenUiEffect()
}