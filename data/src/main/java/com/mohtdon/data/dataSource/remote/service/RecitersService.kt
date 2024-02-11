package com.mohtdon.data.dataSource.remote.service

import com.mohtdon.data.dataSource.remote.response.reciters.RecitersNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecitersService {

    @GET("reciters")
    suspend fun getAllReciters() : Response<RecitersNetworkResponse>
}