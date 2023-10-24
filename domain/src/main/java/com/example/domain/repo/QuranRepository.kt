package com.example.domain.repo

import com.example.domain.models.Aya


interface QuranRepository {
    suspend fun getAyaBySubText(subAya: String) : List<Aya>

    suspend fun getAyaId(ayaId: Int) : String

}