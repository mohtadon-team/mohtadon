package com.example.mohtdon.ui.compose.screen.ahadith

interface AhadithScreenInteraction {
    fun onClickBack()
    fun onClickSearch(value: String)
    fun onToggleSearch()
    fun onSearchValueChange(value: String)
}