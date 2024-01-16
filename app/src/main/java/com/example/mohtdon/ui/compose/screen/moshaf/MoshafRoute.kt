package com.example.mohtdon.ui.compose.screen.moshaf

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addMoshafRoute() {
    composable(
        route = NavigationDestination.ScreenMushaf.route,
    ) {
        ScreenMoshaf()
    }
}

fun NavController.navigateToMoshaf() {
    navigate(NavigationDestination.ScreenMushaf.route)
}