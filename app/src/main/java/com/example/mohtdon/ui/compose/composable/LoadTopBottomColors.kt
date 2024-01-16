package com.example.mohtdon.ui.compose.composable

import androidx.compose.runtime.Composable
import com.example.mohtdon.ui.compose.theme.color_BackgroundColor
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoadTopBottomColors(){
    val sys = rememberSystemUiController()
    sys.setStatusBarColor(color_MainColor,darkIcons = false)
    sys.setNavigationBarColor(color_BackgroundColor,darkIcons = true)
}