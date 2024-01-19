package com.example.mohtdon.ui.compose.screen.azkar

import androidx.lifecycle.SavedStateHandle

class AzkarScreenArgs(savedStateHandle: SavedStateHandle) {

    val azkarTopic: Int = savedStateHandle[AZKAR_TOPIC] ?: 0
    companion object {
        const val AZKAR_TOPIC = "azkar_topic"
    }
}