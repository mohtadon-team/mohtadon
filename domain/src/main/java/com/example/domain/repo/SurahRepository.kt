package com.example.domain.repo


import com.example.domain.entity.AyahResponse
import com.example.domain.entity.Surah
import com.example.domain.entity.SurahResponse

interface SurahRepository {
    suspend fun getAllSurah() : List<Surah>
    suspend fun getSurahAyah(): AyahResponse

}