package com.mohtdon.ui.quran

import com.mohtdon.domain.models.quran.Surah

data class SurahUiState(val surah: Surah? = null,
                        val isLoading:Boolean = false,
                        val error : Boolean = false) {
}