package com.example.mohtdon.ui.compose.screen.hadithtopics

interface HadithTopicsScreenInteraction {
    fun onClickBack()
    fun onClickTopic(topicId: Int)
    fun onToggleSearch()
    fun onSearchValueChange(value: String)
    fun onClickSearch(searchValue: String)
}