package com.example.domain.repo

import com.example.domain.models.QuranResponse

interface QuranRepository {

    suspend fun getAllReciter() : QuranResponse
}