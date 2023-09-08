package com.example.data.dataSource.remote

import com.example.domain.models.QuranResponse
import retrofit2.http.GET


interface ApiService {

    @GET("_arabic.json")
    suspend fun getData() : QuranResponse

}