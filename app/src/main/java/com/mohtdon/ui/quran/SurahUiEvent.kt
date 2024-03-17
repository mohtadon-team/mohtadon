package com.mohtdon.ui.quran

sealed interface SurahUiEvent {
    object getAllSurah: SurahUiEvent
    object clickSurah: SurahUiEvent
}