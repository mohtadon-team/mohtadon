package com.mohtdon.data.dataSource.remote.service

import com.mohtdon.data.dataSource.remote.response.radoi.RadioNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface RadioService {
    @GET("radio-v2/radio_ar.json")
    suspend fun getAllRadioStation() : Response<RadioNetworkResponse>
}