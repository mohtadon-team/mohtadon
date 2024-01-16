package com.example.mohtdon.navigation

sealed class NavigationDestination(val route: String) {
    data object ScreenSplash: NavigationDestination("ScreenSplash")
    data object ScreenHome: NavigationDestination("ScreenHome")
    data object ScreenFollowing: NavigationDestination("ScreenFollowing")
    data object ScreenMushaf: NavigationDestination("ScreenMushaf")

    data object ScreenRadio: NavigationDestination("ScreenRadio")

    data object ScreenSettings: NavigationDestination("ScreenSettings")



}