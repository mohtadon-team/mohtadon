package com.example.data.dataSource.remote.endPoint

import com.example.data.dataSource.remote.responseModels.BaseApiResponse
import com.example.domain.entity.SurahModel
import retrofit2.http.GET

interface QuranApiService {
    @GET("surah")
    suspend fun getAllSurah(): BaseApiResponse<List<SurahModel>>
}