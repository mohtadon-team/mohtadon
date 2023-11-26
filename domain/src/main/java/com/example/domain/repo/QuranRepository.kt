package com.example.domain.repo

import com.example.domain.models.quran.Aya
import com.example.domain.models.quran.PageData


interface QuranRepository {
    suspend fun getAyaBySubText(subAya: String): List<Aya>

    suspend fun getAyaId(ayaId: Int): String
    suspend fun getSoraName(pageNum: Int): PageData

}