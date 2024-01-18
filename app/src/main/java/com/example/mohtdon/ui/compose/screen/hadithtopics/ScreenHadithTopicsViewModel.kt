package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenHadithTopicsViewModel @Inject constructor(

) : BaseViewModel<HadithTopicsUiState, HadithTopicsScreenUiEffect>(HadithTopicsUiState()),
    HadithTopicsScreenInteraction {

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }

    override fun onClickTopic(topicId: Int) {
        sendUiEffect(HadithTopicsScreenUiEffect.NavigateToTopic(topicId))
    }
}