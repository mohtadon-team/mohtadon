package com.example.mohtdon.ui.compose.screen.radio

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler


@Composable
fun ScreenRadio(
    screenRadioViewModel: ScreenRadioViewModel = hiltViewModel()
) {
    val state = screenRadioViewModel.state.collectAsState().value
    ScreenRadioContent(state = state, interaction = screenRadioViewModel)
    NavigationHandler(effects = screenRadioViewModel.effect) { effect, controller ->
        when (effect) {
            is RadioScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRadioContent(
    state: RadioUiState,
    interaction: RadioScreenInteraction
) {

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun RadioTester() {

}