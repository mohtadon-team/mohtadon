package com.example.data.dataSource.remote


import com.example.data.dataSource.remote.endPoint.QuranApiService

import com.example.domain.entity.AyahResponse
import com.example.domain.entity.SurahResponse

import com.example.domain.repo.SurahRepository
import javax.inject.Inject

class SurahRepositoryImpl @Inject constructor(
    private val apiService: QuranApiService
) : SurahRepository {
    override suspend fun getAllSurah(): SurahResponse {
//        val surahDto = apiService.getAllSurah()
////        val surah = convertToSurah(surahDto)
        TODO("Not yet implemented")
    }




    override suspend fun getSurahAyah(): AyahResponse {
        TODO("Not yet implemented")
    }


}


