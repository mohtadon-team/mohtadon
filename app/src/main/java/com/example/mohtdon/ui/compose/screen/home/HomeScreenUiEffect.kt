package com.example.mohtdon.ui.compose.screen.home

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class HomeScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : HomeScreenUiEffect()
    data object NavigateToMenu : HomeScreenUiEffect()
    data object NavigateToPrayerFollowing : HomeScreenUiEffect()
    data object NavigateToMoshaf : HomeScreenUiEffect()
    data object NavigateToAzkar : HomeScreenUiEffect()
    data object NavigateToDuaa : HomeScreenUiEffect()
    data object NavigateToHadith : HomeScreenUiEffect()
    data object NavigateToTafseer : HomeScreenUiEffect()
    data object NavigateToMore : HomeScreenUiEffect()
    data object NavigateToMoreDuaa : HomeScreenUiEffect()
    data object NavigateToMoreAya : HomeScreenUiEffect()
    data object NavigateToMoreAzkar : HomeScreenUiEffect()
}