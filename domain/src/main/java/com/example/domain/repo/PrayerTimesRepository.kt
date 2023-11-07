package com.example.domain.repo

import com.example.domain.models.prayerTimes.MonthPrayerTimes
import com.example.domain.models.todayPrayerTimes.DayPrayerTimes

interface PrayerTimesRepository {
   suspend fun getDayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes


   suspend fun getMonthPrayerTimes(
       month:Int , year:Int ,latitude: Double, longitude: Double
   ): MonthPrayerTimes

}