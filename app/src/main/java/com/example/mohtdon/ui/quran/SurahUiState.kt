package com.example.mohtdon.ui.quran

import com.example.domain.models.quran.Surah

data class SurahUiState(val surah: Surah? = null,
                        val isLoading:Boolean = false,
                        val error : Boolean = false) {
}