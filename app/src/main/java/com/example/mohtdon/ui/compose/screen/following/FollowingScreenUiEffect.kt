package com.example.mohtdon.ui.compose.screen.following

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class FollowingScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : FollowingScreenUiEffect()
}