package com.example.mohtdon.ui.compose.screen.shahada

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
import com.example.mohtdon.ui.compose.composable.MohtdonTopAppBarWithOneAction
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_GreenSubtitleText
import com.example.mohtdon.ui.compose.theme.color_GreenText
import com.example.mohtdon.ui.compose.theme.color_OrangeText
import com.example.mohtdon.ui.compose.theme.color_White


@Composable
fun ScreenShahada(
    screenShahadaViewModel: ScreenShahadaViewModel = hiltViewModel()
) {
    val state = screenShahadaViewModel.state.collectAsState().value
    ScreenShahadaContent(state = state, interaction = screenShahadaViewModel)
    NavigationHandler(effects = screenShahadaViewModel.effect) { effect, controller ->
        when (effect) {
            is ShahadaScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenShahadaContent(
    state: ShahadaUiState,
    interaction: ShahadaScreenInteraction
) {
    MohtdonScaffold(
        topAppbar = {
            MohtdonTopAppBarWithOneAction(stringResource(id = R.string.more_shahada)) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp)
                        .rotate(180f)
                        .align(Alignment.CenterStart)
                        .clickable { interaction.onClickBack() },
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "",
                    tint = color_White
                )
            }
        },
        isLoading = false,
        isError = false
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp,start = 16.dp,end = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.arabic),
                fontFamily = Tajawal,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                color = color_OrangeText,
                softWrap = true
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                text = stringResource(id = R.string.shahada_arabic),
                fontFamily = Tajawal,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                color = color_GreenText,
                softWrap = true
            )
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(color_GreenSubtitleText).padding(vertical = 16.dp))
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.english),
                fontFamily = Tajawal,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                color = color_OrangeText,
                softWrap = true
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                text = stringResource(id = R.string.shahada_english),
                fontFamily = Tajawal,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.End,
                color = color_GreenText,
                softWrap = true
            )
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(color_GreenSubtitleText).padding(vertical = 16.dp))
        }
    }
}