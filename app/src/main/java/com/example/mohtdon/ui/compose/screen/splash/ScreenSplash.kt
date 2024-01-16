package com.example.mohtdon.ui.compose.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.R
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.screen.home.navigateToHome
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_BackgroundColor
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.example.mohtdon.ui.compose.theme.color_White
import com.example.mohtdon.ui.compose.theme.grad_orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun ScreenSplash(
    screenSplashViewModel: ScreenSplashViewModel = hiltViewModel()
) {
    ScreenSplashContent(interaction = screenSplashViewModel)
    NavigationHandler(effects = screenSplashViewModel.effect) { effect, controller ->
        when (effect) {
            is SplashScreenUiEffect.NavigateToHomeScreen -> {
                controller.navigateToHome()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSplashContent(
    interaction: SplashScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color_MainColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                painter = painterResource(id = R.drawable.mohtdon_splash_png),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(id = R.string.to_heaven_partners),
                fontFamily = Tajawal,
                fontWeight = FontWeight.ExtraBold,
                color = color_White,
                fontSize = 30.sp
            )
            val btnShape = RoundedCornerShape(CornerSize(36.dp))
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clip(btnShape)
                    .align(Alignment.CenterHorizontally)
                    .shadow(
                        elevation = 10.dp,
                        btnShape
                    )
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.13f)
                    .background(brush = grad_orange, btnShape)
                    .clickable { interaction.onClickStartNow() },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.start_now),
                    fontFamily = Tajawal,
                    fontWeight = FontWeight.ExtraBold,
                    color = color_White,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}