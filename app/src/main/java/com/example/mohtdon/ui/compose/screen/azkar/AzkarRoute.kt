package com.example.mohtdon.ui.compose.screen.azkar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addAzkarRoute() {
    composable(
        route = "${NavigationDestination.ScreenAzkar.route}/{${AzkarScreenArgs.AZKAR_TOPIC}}",
        arguments = listOf(
            navArgument(AzkarScreenArgs.AZKAR_TOPIC) { type = NavType.IntType }
        )
    ) {
        ScreenAzkar()
    }
}

fun NavController.navigateToAzkar(azkarTopicId: Int) {
    navigate("${NavigationDestination.ScreenAzkar.route}/$azkarTopicId")
}