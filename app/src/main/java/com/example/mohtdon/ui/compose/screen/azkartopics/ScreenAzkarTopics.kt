package com.example.mohtdon.ui.compose.screen.azkartopics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.R
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.AddItemTopic
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.SearchAppBar
import com.example.mohtdon.ui.compose.screen.azkar.navigateToAzkar
import com.example.mohtdon.ui.compose.theme.color_MainColor


@Composable
fun ScreenAzkarTopics(
    screenAzkarTopicsViewModel: ScreenAzkarTopicsViewModel = hiltViewModel()
) {
    val state = screenAzkarTopicsViewModel.state.collectAsState().value
    ScreenAzkarTopicsContent(state = state, interaction = screenAzkarTopicsViewModel)
    NavigationHandler(effects = screenAzkarTopicsViewModel.effect) { effect, controller ->
        when (effect) {
            is AzkarTopicsScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is AzkarTopicsScreenUiEffect.NavigateToTopic -> {
                controller.navigateToAzkar(effect.topicID)
            }
        }
    }
}

@Composable
fun ScreenAzkarTopicsContent(
    state: AzkarTopicsUiState,
    interaction: AzkarTopicsScreenInteraction
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
                    label = stringResource(R.string.home_rv_item_text_2),
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
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 56.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                state.azkarTopics, key = { it.id }
            ) {
                AddItemTopic(
                    id = it.id,
                    name = it.name,
                    onClick = interaction::onAzkarTopicClick
                )
            }
        }
    }
}