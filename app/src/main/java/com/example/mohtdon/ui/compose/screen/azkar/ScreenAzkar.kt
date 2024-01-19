package com.example.mohtdon.ui.compose.screen.azkar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.R
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.MohtdonTopAppBarWithOneAction
import com.example.mohtdon.ui.compose.screen.ahadith.AddTextCardItem
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_AzkarCardColor
import com.example.mohtdon.ui.compose.theme.color_White


@Composable
fun ScreenAzkar(
    screenAzkarViewModel: ScreenAzkarViewModel = hiltViewModel()
) {
    val state = screenAzkarViewModel.state.collectAsState().value
    ScreenAzkarContent(state = state, interaction = screenAzkarViewModel)
    NavigationHandler(effects = screenAzkarViewModel.effect) { effect, controller ->
        when (effect) {
            is AzkarScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

        }
    }
}

@Composable
fun ScreenAzkarContent(
    state: AzkarUiState,
    interaction: AzkarScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false,
        topAppbar = {
            MohtdonTopAppBarWithOneAction(value = state.screenLabel){
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .rotate(180f)
                        .align(Alignment.CenterStart)
                        .clickable { interaction.onClickBack() },
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "",
                    tint = color_White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 56.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(state.azkarList,key =  {
                it.id
            }){
                Box(modifier = Modifier){
                    AddTextCardItem(text = it.text, modifier = Modifier.padding(bottom = 16.dp))
                    Row(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(color_AzkarCardColor)
                            .align(Alignment.BottomEnd)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${it.count}",
                            fontFamily = Tajawal,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = color_White,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
