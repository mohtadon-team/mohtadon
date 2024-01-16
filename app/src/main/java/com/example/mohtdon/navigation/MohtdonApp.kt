package com.example.mohtdon.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mohtdon.ui.compose.composable.BottomBarHandler


val LocalNavController =
    compositionLocalOf<NavHostController> { error("No NavController found!") }

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MohtdonApp() {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
        Scaffold(
            bottomBar = { Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                BottomBarHandler()
            } }
        ){
            MohtdonNavGraph()
        }
    }
}

