package com.mohtdon.domain.repo

import com.mohtdon.domain.models.reciters.ReciterEntity

interface RecitersRepository {
    suspend fun getAllReciters() : List<ReciterEntity>?
}