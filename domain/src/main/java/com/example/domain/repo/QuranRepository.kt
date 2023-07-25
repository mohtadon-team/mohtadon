package com.example.domain.repo

import com.example.domain.entity.QuranResponse

interface QuranRepository {

    suspend fun getAllReciter() : QuranResponse
}