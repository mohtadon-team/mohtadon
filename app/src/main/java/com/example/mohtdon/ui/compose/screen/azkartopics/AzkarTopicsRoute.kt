package com.example.mohtdon.ui.compose.screen.azkartopics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addAzkarTopicsRoute() {
    composable(
        route = NavigationDestination.ScreenAzkarTopics.route,
    ) {
        ScreenAzkarTopics()
    }
}

fun NavController.navigateToAzkarTopics() {
    navigate(NavigationDestination.ScreenAzkarTopics.route)
}