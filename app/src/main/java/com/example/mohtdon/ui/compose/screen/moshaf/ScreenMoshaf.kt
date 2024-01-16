package com.example.mohtdon.ui.compose.screen.moshaf

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold


@Composable
fun ScreenMoshaf(
    screenMoshafViewModel: ScreenMoshafViewModel = hiltViewModel()
) {
    val state = screenMoshafViewModel.state.collectAsState().value
    ScreenMoshafContent(state = state, interaction = screenMoshafViewModel)
    NavigationHandler(effects = screenMoshafViewModel.effect) { effect, controller ->
        when (effect) {
            is MoshafScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMoshafContent(
    state: MoshafUiState,
    interaction: MoshafScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false) {

    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun MoshafTester() {

}