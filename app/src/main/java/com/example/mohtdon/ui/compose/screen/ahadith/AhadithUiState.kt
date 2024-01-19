package com.example.mohtdon.ui.compose.screen.ahadith

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.HadithItem


data class AhadithUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val screenLabel: String = "",
    val isSearchVisible: Boolean = false,
    val searchValue: String = "",
    val ahadith: List<HadithItem> = emptyList()
) : BaseUiState


