package com.example.data.dataSource.remote.mapper

import com.example.data.dataSource.remote.response.todayPrayerTimes.Timings
import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import javax.inject.Inject

class PrayerTimesMapper @Inject constructor() : Mapper<Timings, TodayPrayerTimes> {
    override fun map(input: Timings): TodayPrayerTimes {
        return TodayPrayerTimes(
            Fajr = input.Fajr,
            Dhuhr = input.Dhuhr,
            Asr = input.Asr,
            Maghrib = input.Maghrib,
            Isha = input.Isha
            )
    }
}