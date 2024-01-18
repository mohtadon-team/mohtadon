package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class HadithTopicsScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : HadithTopicsScreenUiEffect()
    data class NavigateToTopic(val topicId: Int): HadithTopicsScreenUiEffect()

}