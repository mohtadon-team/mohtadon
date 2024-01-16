package com.example.mohtdon.ui.compose.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addSplashRoute() {
    composable(
        route = NavigationDestination.ScreenSplash.route,
    ) {
        ScreenSplash()
    }
}

fun NavController.navigateToSplash() {
    navigate(NavigationDestination.ScreenSplash.route)
}