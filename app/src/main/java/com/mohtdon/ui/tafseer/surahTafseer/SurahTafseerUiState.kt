package com.mohtdon.ui.tafseer.surahTafseer

import com.mohtdon.ui.tafseer.surahTafseer.SurahTafseerModel

data class SurahTafseerUiState(
    val surahName:String ="" ,
    val surahAyatText:List<String> = listOf() ,
    val surahTafseer:List<SurahTafseerModel> = listOf()
)
