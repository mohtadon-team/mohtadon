package com.example.data.dataSource.remote.service

import com.example.data.dataSource.remote.response.todayPrayerTimes.TodayPrayerTimesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PrayerTimesService {

    @GET("v1/timings/{date}")
     suspend fun getTodayPrayerTimes(
        @Path("date") date: String ,
        @Query("latitude") latitude: Double ,
        @Query("longitude") longitude: Double  ,
    ): Response<TodayPrayerTimesDto>


}