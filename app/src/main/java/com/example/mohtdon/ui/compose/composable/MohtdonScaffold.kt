package com.example.mohtdon.ui.compose.composable

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mohtdon.R
import com.example.mohtdon.navigation.LocalNavController
import com.example.mohtdon.navigation.NavigationDestination
import com.example.mohtdon.ui.compose.screen.following.navigateToFollowing
import com.example.mohtdon.ui.compose.screen.home.navigateToHome
import com.example.mohtdon.ui.compose.screen.moshaf.navigateToMoshaf
import com.example.mohtdon.ui.compose.screen.radio.navigateToRadio
import com.example.mohtdon.ui.compose.screen.settings.navigateToSettings
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_BackgroundColor
import com.example.mohtdon.ui.compose.theme.color_CardBackground
import com.example.mohtdon.ui.compose.theme.color_SearchTitle
import com.example.mohtdon.ui.compose.theme.color_Sec_Blue
import com.example.mohtdon.ui.compose.theme.color_icon_gray

val bottomNavList = listOf(
    NavigationDestination.ScreenHome.route,
    NavigationDestination.ScreenFollowing.route,
    NavigationDestination.ScreenMushaf.route,
    NavigationDestination.ScreenRadio.route,
    NavigationDestination.ScreenSettings.route,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MohtdonScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isError: Boolean,
    onLoading: @Composable () -> Unit = { MohtdonLoading() },
    onError: @Composable () -> Unit = { MohtdonError() },
    containerColor: Color = Color.White,
    topAppbar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        containerColor = containerColor,
        topBar = topAppbar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
    ) { _ ->
        LoadTopBottomColors()
        AnimatedVisibility(visible = isLoading) {
            onLoading()
        }
        AnimatedVisibility(visible = isError) {
            onError()
        }
        AnimatedVisibility(visible = !isLoading && !isError) {
            content()
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route

@Composable
fun BottomBarHandler() {
    val controller = LocalNavController.current
    val currentScreen = currentRoute(navController = controller)
    if (bottomNavList.contains(currentScreen)) {
        BottomNavigation(
            backgroundColor = color_BackgroundColor
        ) {
            AddBottomItem(
                icon = R.drawable.quran_orangeicon,
                label = R.string.bottomnav_mushaf,
                isCurrent = currentScreen == NavigationDestination.ScreenMushaf.route
            ) {
                if (currentScreen != NavigationDestination.ScreenMushaf.route) {
                    controller.navigateToMoshaf()
                }
            }
            AddBottomItem(
                icon = R.drawable.check,
                label = R.string.bottomnav_following,
                isCurrent = currentScreen == NavigationDestination.ScreenFollowing.route
            ) {
                if (currentScreen != NavigationDestination.ScreenFollowing.route) {
                    controller.navigateToFollowing()
                }

            }
            AddBottomItem(
                icon = R.drawable.home,
                label = R.string.bottomnav_home,
                isCurrent = currentScreen == NavigationDestination.ScreenHome.route
            ) {
                if (currentScreen != NavigationDestination.ScreenHome.route) {
                    controller.navigateToHome()
                }
            }
            AddBottomItem(
                icon = R.drawable.radio,
                label = R.string.bottomnav_radio,
                isCurrent = currentScreen == NavigationDestination.ScreenRadio.route
            ) {
                if (currentScreen != NavigationDestination.ScreenRadio.route) {
                    controller.navigateToRadio()
                }
            }
            AddBottomItem(
                icon = R.drawable.settings,
                label = R.string.bottomnav_settings,
                isCurrent = currentScreen == NavigationDestination.ScreenSettings.route
            ) {
                if (currentScreen != NavigationDestination.ScreenSettings.route) {
                    controller.navigateToSettings()
                }
            }
        }
    }
}

@Composable
fun RowScope.AddBottomItem(
    @DrawableRes icon: Int,
    @StringRes label: Int,
    contentDesc: String = "",
    isCurrent: Boolean,
    onClick: () -> Unit
) {
    val currentColor by animateColorAsState(
        targetValue = if (isCurrent) color_Sec_Blue else color_icon_gray,
        label = "",
        animationSpec = tween(
            durationMillis = 300
        )
    )
    BottomNavigationItem(
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = icon),
                contentDescription = contentDesc,
                tint = currentColor
            )
        },
        label = {
            Text(
                stringResource(id = label),
                color = currentColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontFamily = Tajawal
            )
        },
        selected = isCurrent,
        onClick = { onClick() }
    )
}