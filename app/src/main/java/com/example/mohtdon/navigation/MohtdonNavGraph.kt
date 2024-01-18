package com.example.mohtdon.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mohtdon.ui.compose.screen.following.addFollowingRoute
import com.example.mohtdon.ui.compose.screen.hadithtopics.addHadithTopicsRoute
import com.example.mohtdon.ui.compose.screen.home.addHomeRoute
import com.example.mohtdon.ui.compose.screen.more.addMoreRoute
import com.example.mohtdon.ui.compose.screen.moshaf.addMoshafRoute
import com.example.mohtdon.ui.compose.screen.radio.addRadioRoute
import com.example.mohtdon.ui.compose.screen.settings.addSettingsRoute
import com.example.mohtdon.ui.compose.screen.shahada.addShahadaRoute
import com.example.mohtdon.ui.compose.screen.splash.addSplashRoute


@Composable
fun MohtdonNavGraph() {
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
        addFollowingRoute()
        addRadioRoute()
        addSettingsRoute()
        addMoreRoute()
        addMoshafRoute()
        addShahadaRoute()
        addHadithTopicsRoute()
    }
}