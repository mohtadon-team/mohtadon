package com.example.mohtdon.ui.quran

sealed interface SurahUiEvent {
    object getAllSurah:SurahUiEvent
    object clickSurah:SurahUiEvent
}