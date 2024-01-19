package com.example.mohtdon.ui.compose.screen.hadithtopics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.mohtdon.ui.compose.composable.AddItemTopic
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.SearchAppBar
import com.example.mohtdon.ui.compose.screen.ahadith.navigateToAhadith
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.example.mohtdon.ui.compose.theme.color_White

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
                controller.navigateToAhadith(effect.topicId)
            }
        }
    }
}

@Composable
fun ScreenHadithTopicsContent(
    state: HadithTopicsUiState,
    interaction: HadithTopicsScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false,
        topAppbar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(color_MainColor)
            ) {
                SearchAppBar(
                    value = state.searchValue,
                    label = stringResource(R.string.home_rv_item_text_4),
                    isSearchVisible = state.isSearchVisible,
                    onClickBack = interaction::onClickBack,
                    onClickSearch = interaction::onClickSearch,
                    onToggleSearch = interaction::onToggleSearch,
                    onSearchValueChange = interaction::onSearchValueChange,
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                state.topics, key = { it.id }
            ) {
                AddItemTopic(name = it.name, id = it.id) { id ->
                    interaction.onClickTopic(id)
                }
            }
        }
    }
}