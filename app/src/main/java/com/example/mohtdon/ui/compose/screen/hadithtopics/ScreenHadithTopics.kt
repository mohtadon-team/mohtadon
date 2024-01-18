package com.example.mohtdon.ui.compose.screen.hadithtopics

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.example.mohtdon.ui.compose.theme.color_OrangeText
import com.example.mohtdon.ui.compose.theme.color_White
import com.example.mohtdon.ui.compose.theme.color_White_60


@Composable
fun ScreenHadithTopics(
    screenHadithTopicsViewModel: ScreenHadithTopicsViewModel = hiltViewModel()
) {
    val state = screenHadithTopicsViewModel.state.collectAsState().value
    ScreenHadithTopicsContent(state = state, interaction = screenHadithTopicsViewModel)
    NavigationHandler(effects = screenHadithTopicsViewModel.effect) { effect, controller ->
        when (effect) {
            is HadithTopicsScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is HadithTopicsScreenUiEffect.NavigateToTopic -> {
                //TODO
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHadithTopicsContent(
    state: HadithTopicsUiState,
    interaction: HadithTopicsScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false,
        topAppbar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color_MainColor)
            ){
                AnimatedVisibility(visible = !state.isSearchVisible,
                    enter = fadeIn(
                        animationSpec = tween(300)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(300)
                    )
                ) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(180f)
                                .clickable { interaction.onClickBack() },
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = ""
                        )
                        Text(
                            text = stringResource(id = R.string.home_rv_item_text_4),
                            fontFamily = Tajawal,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f),
                            color = color_White
                        )
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { interaction.onToggleSearch() },
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = ""
                        )
                    }
                }
                AnimatedVisibility(visible = state.isSearchVisible,
                    enter = slideInHorizontally(
                        animationSpec = tween(300)
                    ),
                    exit = slideOutHorizontally(
                        animationSpec = tween(300)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                                .rotate(180f)
                                .clickable { interaction.onToggleSearch() },
                            painter = painterResource(id = R.drawable.close_ic),
                            contentDescription = ""
                        )
                        val shape = RoundedCornerShape(CornerSize(16.dp))
                        Row(
                            modifier = Modifier
                                .border(border = BorderStroke(1.dp,color = color_White_60),shape)
                                .clip(shape)
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(end = 16.dp)
                                ,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End,
                        ) {
                            TextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp),
                                value = state.searchValue,
                                onValueChange = interaction::onSearchValueChange,
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedPlaceholderColor = color_White_60,
                                    unfocusedPlaceholderColor = color_White_60,
                                    focusedTextColor = color_White,
                                    unfocusedTextColor = color_White
                                )
                            )
                            Image(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { interaction.onClickSearch(state.searchValue) },
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = ""
                            )
                        }

                    }
                }
            }

        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(state.topics) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(CornerSize(16.dp)))
                        .clickable { interaction.onClickTopic(it.id) }
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color_MainColor)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.azkar_category),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.name,
                            fontFamily = Tajawal,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            color = color_White,
                            softWrap = true
                        )
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.right_arrow),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun HadithTopicsTester() {

}