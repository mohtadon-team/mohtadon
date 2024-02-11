package com.mohtdon.domain.repo

import com.mohtdon.domain.entity.reciters.ReciterEntity

interface RecitersRepository {
    suspend fun getAllReciters() : List<ReciterEntity>?
}