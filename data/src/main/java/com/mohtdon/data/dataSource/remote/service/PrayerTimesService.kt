package com.mohtdon.data.dataSource.remote.service

import com.mohtdon.data.dataSource.remote.response.DayPrayerTimes.DayPrayerTimesDto
import com.mohtdon.data.dataSource.remote.response.monthPrayerTimes.MonthPrayerTimesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PrayerTimesService {

    @GET("v1/timings/{date}")
    suspend fun getDayPrayerTimes(
        @Path("date") date: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): Response<DayPrayerTimesDto>


    @GET("v1/calendar")
    suspend fun getMonthPrayerTimes(
        @Query("month") month: Int,
        @Query("year") year: Int,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double ,
        @Query("method") method:Int = 4
    ): Response<MonthPrayerTimesDto>


}