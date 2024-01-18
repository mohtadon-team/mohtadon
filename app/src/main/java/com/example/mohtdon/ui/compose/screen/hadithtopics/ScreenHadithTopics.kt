package com.example.mohtdon.ui.compose.screen.hadithtopics

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold


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
    MohtdonScaffold(isLoading = false, isError = false) {

    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun HadithTopicsTester() {

}