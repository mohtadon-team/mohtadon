package com.example.mohtdon.ui.compose.screen.azkar

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.AzkarItem


data class AzkarUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val screenLabel: String = "",
    val azkarList: List<AzkarItem> = emptyList()
) : BaseUiState