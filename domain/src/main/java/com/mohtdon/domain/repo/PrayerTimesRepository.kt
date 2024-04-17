package com.mohtdon.domain.repo

import com.mohtdon.domain.models.prayerTimes.MonthPrayerTimes
import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes

interface PrayerTimesRepository {
   suspend fun getDayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes?


   suspend fun getMonthPrayerTimes(
       month:Int , year:Int ,latitude: Double, longitude: Double
   ): MonthPrayerTimes

}