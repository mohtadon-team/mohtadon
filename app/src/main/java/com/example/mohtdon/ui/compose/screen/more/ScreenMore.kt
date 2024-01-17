package com.example.mohtdon.ui.compose.screen.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.mohtdon.ui.compose.composable.HomeItemCard
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.MohtdonTopAppBarWithOneAction
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_GreenText
import com.example.mohtdon.ui.compose.theme.color_White


@Composable
fun ScreenMore(
    screenMoreViewModel: ScreenMoreViewModel = hiltViewModel()
) {
    val state = screenMoreViewModel.state.collectAsState().value
    ScreenMoreContent(state = state, interaction = screenMoreViewModel)
    NavigationHandler(effects = screenMoreViewModel.effect) { effect, controller ->
        when (effect) {
            is MoreScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is MoreScreenUiEffect.NavigateToAllahNames -> { //TODO

            }

            is MoreScreenUiEffect.NavigateToClosestMosque -> { //TODO
                
            }

            is MoreScreenUiEffect.NavigateToHijryCalender -> {//TODO

            }

            is MoreScreenUiEffect.NavigateToMakka -> {//TODO

            }

            is MoreScreenUiEffect.NavigateToQiblah -> {//TODO

            }

            is MoreScreenUiEffect.NavigateToSebha -> {//TODO

            }

            is MoreScreenUiEffect.NavigateToShahada -> {//TODO

            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenMoreContent(
    state: MoreUiState,
    interaction: MoreScreenInteraction
) {
    MohtdonScaffold(
        topAppbar = {
            MohtdonTopAppBarWithOneAction(stringResource(id = R.string.more)) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp,top = 8.dp)
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
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 88.dp, start = 16.dp, end = 16.dp),
            maxItemsInEachRow = 4
        ) {
            for (i in state.moreItems) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .padding(bottom = 8.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(75.dp),
                        onClick = { interaction.onClickItem(i.id) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = color_White,
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.size(36.dp),
                                painter = painterResource(id = i.icon),
                                contentDescription = "",
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = stringResource(id = i.label),
                        fontFamily = Tajawal,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = color_GreenText,
                        softWrap = true
                    )
                }
            }
        }
    }
}