package com.example.mohtdon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost


@Composable
fun MohtdonNavGraph() {
    NavHost(
        navController = LocalNavController.current,
        startDestination = ""
    ) {

    }
}