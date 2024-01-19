package com.example.mohtdon.ui.compose.screen.azkartopics

interface AzkarTopicsScreenInteraction {
    fun onClickBack()
    fun onAzkarTopicClick(topicId: Int)

    fun onClickSearch(value: String)
    fun onToggleSearch()
    fun onSearchValueChange(value: String)
}