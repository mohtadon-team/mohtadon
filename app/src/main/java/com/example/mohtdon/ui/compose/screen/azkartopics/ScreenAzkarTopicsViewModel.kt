package com.example.mohtdon.ui.compose.screen.azkartopics

import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.data.azkarTopicsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenAzkarTopicsViewModel @Inject constructor(
) : BaseViewModel<AzkarTopicsUiState, AzkarTopicsScreenUiEffect>(AzkarTopicsUiState()),
    AzkarTopicsScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                azkarTopics = azkarTopicsList
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(AzkarTopicsScreenUiEffect.NavigateUp)
    }

    override fun onAzkarTopicClick(topicId: Int) {
        sendUiEffect(AzkarTopicsScreenUiEffect.NavigateToTopic(topicId))
    }

    override fun onClickSearch(value: String) {
        //TODO
    }

    override fun onToggleSearch() {
        iState.update {
            it.copy(
                isSearchVisible = !it.isSearchVisible
            )
        }
    }

    override fun onSearchValueChange(value: String) {
        iState.update {
            it.copy(
                searchValue = value
            )
        }
    }
}