package com.example.mohtdon.ui.compose.screen.moshaf

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.mohtdon.ui.compose.base.BaseUiState
import com.example.mohtdon.ui.compose.base.ErrorUiState


data class MoshafUiState(
    val isLoading: Boolean = true,
    val error: ErrorUiState = ErrorUiState(),
    val items: List<MoshafItem> = emptyList()
) : BaseUiState


data class MoshafItem(
    val id: Int,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
)