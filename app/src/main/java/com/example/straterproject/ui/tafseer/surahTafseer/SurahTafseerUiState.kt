package com.example.straterproject.ui.tafseer.surahTafseer

data class SurahTafseerUiState(
    val surahName:String ="" ,
    val surahAyatText:List<String> = listOf() ,
    val surahTafseer:List<SurahTafseerModel> = listOf()
)
