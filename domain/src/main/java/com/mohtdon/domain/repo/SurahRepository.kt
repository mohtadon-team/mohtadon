package com.mohtdon.domain.repo



import com.mohtdon.domain.models.quran.Surah


interface SurahRepository {
    suspend fun getAllSurah() : List<Surah>


}