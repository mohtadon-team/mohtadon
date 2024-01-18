package com.example.mohtdon.ui.compose.screen.hadithtopics

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addHadithTopicsRoute() {
    composable(
        route = NavigationDestination.ScreenHadithTopics.route,
    ) {
        ScreenHadithTopics()
    }
}

fun NavController.navigateToHadithTopics() {
    navigate(NavigationDestination.ScreenHadithTopics.route)
}