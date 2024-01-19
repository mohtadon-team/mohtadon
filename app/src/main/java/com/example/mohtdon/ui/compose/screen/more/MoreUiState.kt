package com.example.mohtdon.ui.compose.screen.more

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.HomeRowItems


data class MoreUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val moreItems: List<HomeRowItems> = emptyList()
) : BaseUiState