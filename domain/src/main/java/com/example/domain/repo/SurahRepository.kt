package com.example.domain.repo



import com.example.domain.entity.Surah


interface SurahRepository {
    suspend fun getAllSurah() : List<Surah>


}