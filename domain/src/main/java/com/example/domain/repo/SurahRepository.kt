package com.example.domain.repo



import com.example.domain.models.quran.Surah


interface SurahRepository {
    suspend fun getAllSurah() : List<Surah>


}