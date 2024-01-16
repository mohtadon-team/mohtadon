package com.example.mohtdon.navigation

sealed class NavigationDestination(val route: String) {
    data object ScreenSplash: NavigationDestination("ScreenSplash")
    data object ScreenHome: NavigationDestination("ScreenHome")

}