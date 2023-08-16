package com.example.data.dataSource.remote.endPoint

import com.example.data.dataSource.remote.response.quran.AyahDto
import com.example.data.dataSource.remote.response.quran.SurahDto
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET("surah")
    suspend fun getAllSurah(): SurahDto
    @GET("surah/{surahNum}")
    suspend fun getAyahsSurah(@Path("surahNum") surahNum: Int) : AyahDto
}