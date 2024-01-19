package com.example.mohtdon.ui.compose.screen.home

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState
import com.example.mohtdon.ui.compose.data.HomeRowItems

data class HomeUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val homeItems: List<HomeRowItems> = emptyList(),
    val todayDate: String = "",
    val todayDuaa: String = "",
    val todayAya: String = "",
    val todayAzkar: String = "",
    val nextPrayer: String = "",
) : BaseUiState