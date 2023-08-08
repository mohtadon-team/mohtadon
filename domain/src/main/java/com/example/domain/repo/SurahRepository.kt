package com.example.domain.repo

import com.example.domain.entity.SurahAyatResponse
import com.example.domain.entity.SurahResponse

interface SurahRepository {
    suspend fun getAllSurah() :SurahResponse
    suspend fun getSurahAyah():SurahAyatResponse
}