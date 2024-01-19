
package com.example.mohtdon.ui.compose.screen.ahadith

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mohtdon.navigation.NavigationDestination

fun NavGraphBuilder.addAhadithRoute() {
    composable(
        route = "${NavigationDestination.ScreenAhadith.route}/{${AhadithScreenArgs.TOPICS_ID}}",
        arguments = listOf(
            navArgument(AhadithScreenArgs.TOPICS_ID) { type = NavType.IntType }
        )
    ) {
        ScreenAhadith()
    }
}

fun NavController.navigateToAhadith(topicID: Int) {
    navigate("${NavigationDestination.ScreenAhadith.route}/$topicID")
}