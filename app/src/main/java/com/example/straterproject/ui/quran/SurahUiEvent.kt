package com.example.straterproject.ui.quran

sealed interface SurahUiEvent {
    object getAllSurah:SurahUiEvent
    object clickSurah:SurahUiEvent
}