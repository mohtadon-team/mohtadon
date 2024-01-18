package com.example.mohtdon.ui.compose.screen.hadithtopics

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState


data class HadithTopicsUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState()
) : BaseUiState