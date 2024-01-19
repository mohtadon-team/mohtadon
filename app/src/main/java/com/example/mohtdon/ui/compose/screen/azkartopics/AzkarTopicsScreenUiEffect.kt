package com.example.mohtdon.ui.compose.screen.azkartopics

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class AzkarTopicsScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : AzkarTopicsScreenUiEffect()
    data class NavigateToTopic(val topicID: Int) : AzkarTopicsScreenUiEffect()
}