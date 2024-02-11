package com.mohtdon.data.dataSource.remote.service

import com.mohtdon.data.dataSource.remote.response.quran.AyahDto
import com.mohtdon.data.dataSource.remote.response.quran.SurahDto
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {

    @GET("chapters?language=en")
    suspend fun getAllSurah(): SurahDto
    @GET("surah/{surahNum}")
    suspend fun getAyahsSurah(@Path("surahNum") surahNum: Int) : AyahDto
}