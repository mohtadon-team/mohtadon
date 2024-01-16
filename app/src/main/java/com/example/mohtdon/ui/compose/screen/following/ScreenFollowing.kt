package com.example.mohtdon.ui.compose.screen.following

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold


@Composable
fun ScreenFollowing(
    screenFollowingViewModel: ScreenFollowingViewModel = hiltViewModel()
) {
    val state = screenFollowingViewModel.state.collectAsState().value
    ScreenFollowingContent(state = state, interaction = screenFollowingViewModel)
    NavigationHandler(effects = screenFollowingViewModel.effect) { effect, controller ->
        when (effect) {
            is FollowingScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFollowingContent(
    state: FollowingUiState,
    interaction: FollowingScreenInteraction
) {
    MohtdonScaffold(isLoading = false, isError = false) {

    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun FollowingTester() {

}