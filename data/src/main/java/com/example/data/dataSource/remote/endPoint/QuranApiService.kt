package com.example.data.dataSource.remote.endPoint

import com.example.data.dataSource.remote.responseModels.BaseApiResponse
import com.example.domain.models.AyahsItem
import com.example.domain.models.SurahModel
import com.example.data.dataSource.remote.response.quran.AyahDto
import com.example.data.dataSource.remote.response.quran.SurahDto
import com.example.domain.entity.Surah
import com.example.domain.entity.SurahResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("chapters?language=en")
    suspend fun getAllSurah(): SurahDto
    @GET("surah/{surahNum}")
    suspend fun getAyahsSurah(@Path("surahNum") surahNum: Int) : AyahDto
}