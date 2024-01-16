package com.example.mohtdon.ui.compose.screen.radio

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addRadioRoute() {
    composable(
        route = NavigationDestination.ScreenRadio.route,
    ) {
        ScreenRadio()
    }
}

fun NavController.navigateToRadio() {
    navigate(NavigationDestination.ScreenRadio.route)
}