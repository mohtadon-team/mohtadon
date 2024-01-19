package com.example.mohtdon.ui.compose.screen.ahadith

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.SearchAppBar
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_AzkarCardColor
import com.example.mohtdon.ui.compose.theme.color_GreenText
import com.example.mohtdon.ui.compose.theme.color_MainColor


@Composable
fun ScreenAhadith(
    screenAhadithViewModel: ScreenAhadithViewModel = hiltViewModel()
) {
    val state = screenAhadithViewModel.state.collectAsState().value
    ScreenAhadithContent(state = state, interaction = screenAhadithViewModel)
    NavigationHandler(effects = screenAhadithViewModel.effect) { effect, controller ->
        when (effect) {
            is AhadithScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}


@Composable
fun ScreenAhadithContent(
    state: AhadithUiState,
    interaction: AhadithScreenInteraction
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
                    label = state.screenLabel,
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
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 56.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(state.ahadith){
                AddHadithItem(it.text)
            }
        }
    }
}

@Composable
fun AddHadithItem(text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(18.dp)),
        border = BorderStroke(1.dp, color = color_AzkarCardColor),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(
            text = text,
            fontFamily = Tajawal,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = color_GreenText,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}