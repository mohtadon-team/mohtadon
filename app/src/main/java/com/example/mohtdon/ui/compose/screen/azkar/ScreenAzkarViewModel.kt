package com.example.mohtdon.ui.compose.screen.azkar

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.data.azkarList
import com.example.mohtdon.ui.compose.data.azkarTopicsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenAzkarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<AzkarUiState, AzkarScreenUiEffect>(AzkarUiState()), AzkarScreenInteraction {

    private val args: AzkarScreenArgs = AzkarScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        val label  = azkarTopicsList[args.azkarTopic - 1].name
        iState.update {
            it.copy(
                screenLabel = label,
                azkarList = azkarList
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(AzkarScreenUiEffect.NavigateUp)
    }
}