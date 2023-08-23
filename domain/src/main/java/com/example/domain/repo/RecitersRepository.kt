package com.example.domain.repo

import com.example.domain.entity.reciters.ReciterEntity

interface RecitersRepository {
    suspend fun getAllReciters() : List<ReciterEntity>?
}