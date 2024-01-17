package com.example.mohtdon.ui.compose.screen.more

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addMoreRoute() {
    composable(
        route = NavigationDestination.ScreenMore.route,
    ) {
        ScreenMore()
    }
}

fun NavController.navigateToMore() {
    navigate(NavigationDestination.ScreenMore.route)
}