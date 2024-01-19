package com.example.mohtdon.ui.compose.screen.azkartopics

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.TopicItem


data class AzkarTopicsUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val isSearchVisible: Boolean = false,
    val searchValue: String = "",
    val azkarTopics: List<TopicItem> = emptyList(),
) : BaseUiState