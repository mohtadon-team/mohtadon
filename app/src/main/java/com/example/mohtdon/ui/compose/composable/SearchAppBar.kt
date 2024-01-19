package com.example.mohtdon.ui.compose.composable

import androidx.compose.runtime.Composable

@Composable
fun SearchAppBar(
    label: String,
    isSearchVisible: Boolean,
    value: String,
    onClickBack: () -> Unit,
    onToggleSearch: () -> Unit,
    onSearchValueChange: (String) -> Unit,
    onClickSearch: (String) -> Unit
) {
    SearchNotVisible(
        label = label,
        visibility = !isSearchVisible,
        onClickBack = onClickBack,
        onToggleSearch = onToggleSearch
    )
    SearchVisible(
        visibility = isSearchVisible,
        value = value,
        onToggleSearch = onToggleSearch,
        onSearchValueChange = onSearchValueChange,
        onClickSearch = onClickSearch
    )
}