package com.example.domain.repo

import com.example.domain.models.SurahAyatResponse
import com.example.domain.models.SurahResponse

interface SurahRepository {
    suspend fun getAllSurah() :SurahResponse
    suspend fun getSurahAyah():SurahAyatResponse
}