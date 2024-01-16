package com.example.mohtdon.ui.compose.screen.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addSettingsRoute() {
    composable(
        route = NavigationDestination.ScreenSettings.route,
    ) {
        ScreenSettings()
    }
}

fun NavController.navigateToSettings() {
    navigate(NavigationDestination.ScreenSettings.route)
}