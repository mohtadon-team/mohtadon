package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.data.hadithTopicsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenHadithTopicsViewModel @Inject constructor(

) : BaseViewModel<HadithTopicsUiState, HadithTopicsScreenUiEffect>(HadithTopicsUiState()),
    HadithTopicsScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                topics = hadithTopicsList
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(HadithTopicsScreenUiEffect.NavigateUp)
    }

    override fun onClickTopic(topicId: Int) {
        sendUiEffect(HadithTopicsScreenUiEffect.NavigateToTopic(topicId))
    }

    override fun onSearchValueChange(value: String) {
        iState.update {
            it.copy(
                searchValue = value,
            )
        }
    }

    override fun onToggleSearch() {
        iState.update {
            it.copy(
                searchValue = it.searchValue,
                isSearchVisible = !it.isSearchVisible
            )
        }
    }

    override fun onClickSearch(searchValue: String) {
        //TODO Search with the value
    }
}