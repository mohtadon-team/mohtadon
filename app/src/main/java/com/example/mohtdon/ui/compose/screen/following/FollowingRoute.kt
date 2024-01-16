package com.example.mohtdon.ui.compose.screen.following

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addFollowingRoute() {
    composable(
        route = NavigationDestination.ScreenFollowing.route,
    ) {
        ScreenFollowing()
    }
}

fun NavController.navigateToFollowing() {
    navigate(NavigationDestination.ScreenFollowing.route)
}