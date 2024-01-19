package com.example.mohtdon.ui.compose.screen.ahadith

import androidx.lifecycle.SavedStateHandle

class AhadithScreenArgs(savedStateHandle: SavedStateHandle) {

    val topicsId: Int = savedStateHandle[TOPICS_ID] ?: 0
    companion object {
        const val TOPICS_ID = "topics_Id"
    }
}