package com.example.straterproject.ui.quran

import com.example.domain.entity.Surah

data class SurahUiState(val surah: Surah? = null,
                        val isLoading:Boolean = false,
                        val error : Boolean = false) {
}