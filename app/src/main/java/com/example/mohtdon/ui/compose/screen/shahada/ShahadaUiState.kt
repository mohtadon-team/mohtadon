package com.example.mohtdon.ui.compose.screen.shahada

import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState


data class ShahadaUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState()
) : BaseUiState