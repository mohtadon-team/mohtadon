package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.HadithTopicItem


data class HadithTopicsUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val topics: List<HadithTopicItem> = emptyList(),
    val isSearchVisible: Boolean = false,
    val searchValue: String = ""
    ) : BaseUiState





