package com.example.mohtdon.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.mohtdon.ui.compose.screen.home.addHomeRoute
import com.example.mohtdon.ui.compose.screen.splash.addSplashRoute
import com.example.mohtdon.ui.compose.theme.color_BackgroundColor
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MohtdonNavGraph() {
    val sys = rememberSystemUiController()
    sys.setStatusBarColor(color_MainColor,darkIcons = false)
    sys.setNavigationBarColor(color_BackgroundColor,darkIcons = true)
    NavHost(
        navController = LocalNavController.current,
        startDestination = NavigationDestination.ScreenSplash.route,
        enterTransition = {
            scaleIn(
                tween(
                    300
                )
            )
        },
        exitTransition = {
            scaleOut(
                tween(
                    300
                )
            )
        }
    ) {
        addSplashRoute()
        addHomeRoute()
    }
}