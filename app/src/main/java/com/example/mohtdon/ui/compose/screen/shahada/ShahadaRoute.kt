package com.example.mohtdon.ui.compose.screen.shahada

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addShahadaRoute() {
    composable(
        route = NavigationDestination.ScreenShahada.route,
    ) {
        ScreenShahada()
    }
}

fun NavController.navigateToShahada() {
    navigate(NavigationDestination.ScreenShahada.route)
}