package com.example.mohtdon.ui.compose.screen.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.navigation.NavigationHandler


@Composable
fun ScreenSettings(
    screenSettingsViewModel: ScreenSettingsViewModel = hiltViewModel()
) {
    val state = screenSettingsViewModel.state.collectAsState().value
    ScreenSettingsContent(state = state, interaction = screenSettingsViewModel)
    NavigationHandler(effects = screenSettingsViewModel.effect) { effect, controller ->
        when (effect) {
            is SettingsScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSettingsContent(
    state: SettingsUiState,
    interaction: SettingsScreenInteraction
) {

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun SettingsTester() {

}