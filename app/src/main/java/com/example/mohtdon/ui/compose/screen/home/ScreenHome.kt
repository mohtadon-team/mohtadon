package com.example.mohtdon.ui.compose.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.R
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.AddHeaderWithSeeMore
import com.example.mohtdon.ui.compose.composable.HomeItemCard
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.MohtdonTopAppBarWithOneAction
import com.example.mohtdon.ui.compose.screen.hadithtopics.navigateToHadithTopics
import com.example.mohtdon.ui.compose.screen.more.navigateToMore
import com.example.mohtdon.ui.compose.screen.moshaf.navigateToMoshaf
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_GreenText
import com.example.mohtdon.ui.compose.theme.color_OrangeText
import com.example.mohtdon.ui.compose.theme.color_Sec_Blue
import com.example.mohtdon.ui.compose.theme.color_White
import com.example.mohtdon.ui.compose.theme.color_White_60


@Composable
fun ScreenHome(
    screenHomeViewModel: ScreenHomeViewModel = hiltViewModel()
) {
    val state = screenHomeViewModel.state.collectAsState().value
    ScreenHomeContent(state = state, interaction = screenHomeViewModel)
    NavigationHandler(effects = screenHomeViewModel.effect) { effect, controller ->
        when (effect) {
            is HomeScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is HomeScreenUiEffect.NavigateToAzkar -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToDuaa -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToHadith -> {
                controller.navigateToHadithTopics()
            }

            is HomeScreenUiEffect.NavigateToMenu -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToMore -> {
                controller.navigateToMore()
            }

            is HomeScreenUiEffect.NavigateToMoreAya -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToMoreAzkar -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToMoreDuaa -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToMoshaf -> {
                controller.navigateToMoshaf()
            }

            is HomeScreenUiEffect.NavigateToPrayerFollowing -> {
                //TODO
            }

            is HomeScreenUiEffect.NavigateToTafseer -> {
                //TODO
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHomeContent(
    state: HomeUiState,
    interaction: HomeScreenInteraction
) {
    MohtdonScaffold(
        isLoading = false, isError = false,
        topAppbar = {
            MohtdonTopAppBarWithOneAction(stringResource(id = R.string.logo)){
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .rotate(90f)
                        .align(Alignment.CenterStart)
                        .clickable { interaction.onClickMenu() },
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = "",
                    tint = color_White
                )
            }
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp, bottom = 58.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.welcome),
                    fontFamily = Tajawal,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = color_OrangeText,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Text(
                    text = state.todayDate,
                    fontFamily = Tajawal,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = color_GreenText,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .padding(horizontal = 16.dp),
                    onClick = { interaction.onClickPrayerFollowing() },
                    colors = CardDefaults.cardColors(
                        containerColor = color_Sec_Blue,
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = stringResource(id = R.string.next_prayer),
                                    fontFamily = Tajawal,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    color = color_White_60
                                )
                                Text(
                                    text = state.nextPrayer,
                                    fontFamily = Tajawal,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start,
                                    color = color_White
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        modifier = Modifier.padding(bottom = 16.dp),
                                        text = stringResource(id = R.string.track_prayer),
                                        fontFamily = Tajawal,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Start,
                                        color = color_White
                                    )
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(bottom = 8.dp),
                                        painter = painterResource(id = R.drawable.left_arrow),
                                        contentDescription = "",
                                        tint = color_OrangeText
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Column(
                                modifier = Modifier,
                                horizontalAlignment = Alignment.End
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .padding(top = 16.dp)
                                        .align(Alignment.End),
                                    painter = painterResource(id = R.drawable.not_silent),
                                    contentDescription = "",
                                    tint = color_White
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Image(
                                    painter = painterResource(id = R.drawable.nabawi_mosque),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(state.homeItems) {
                        HomeItemCard(it){id ->
                            interaction.onClickHomeRowItem(id)
                        }
                    }
                }
            }
            item {
                AddHeaderWithSeeMore(label = R.string.today_label_duaa) {
                    interaction.onClickMoreDuaa()
                }
                Card(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, color_OrangeText)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = state.todayDuaa,
                        fontFamily = Tajawal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = color_GreenText
                    )
                }
            }
            item {
                AddHeaderWithSeeMore(label = R.string.today_label_aya) {
                    interaction.onClickMoreDuaa()
                }
                Card(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, color_OrangeText)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = state.todayAya,
                        fontFamily = Tajawal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = color_GreenText
                    )
                }
            }
            item {
                AddHeaderWithSeeMore(label = R.string.today_label_zekr) {
                    interaction.onClickMoreDuaa()
                }
                Card(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, color_OrangeText)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        text = state.todayAzkar,
                        fontFamily = Tajawal,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = color_GreenText
                    )
                }
            }
        }
    }
}