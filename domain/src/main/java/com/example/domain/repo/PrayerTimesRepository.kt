package com.example.domain.repo

import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes

interface PrayerTimesRepository {
   suspend fun getTodayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): TodayPrayerTimes
}