package com.example.data.dataSource.remote.endPoint

import com.example.data.dataSource.remote.responseModels.BaseApiResponse
import com.example.domain.models.AyahsItem
import com.example.domain.models.SurahModel
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    suspend fun getAllSurah(): BaseApiResponse<List<SurahModel>>
    @GET("surah/{surahNum}")
    suspend fun getAyahsSurah(@Path("surahNum") surahNum: Int) :BaseApiResponse<AyahsItem>
}