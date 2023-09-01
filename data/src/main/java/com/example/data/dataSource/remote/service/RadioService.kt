package com.example.data.dataSource.remote.service

import com.example.data.dataSource.remote.response.radoi.RadioNetworkResponse
import com.example.data.dataSource.remote.response.reciters.RecitersNetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RadioService {
    @GET("radio-v2/radio_ar.json")
    suspend fun getAllRadioStation() : Response<RadioNetworkResponse>
}