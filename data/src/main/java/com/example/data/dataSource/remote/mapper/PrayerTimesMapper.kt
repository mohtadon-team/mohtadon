package com.example.data.dataSource.remote.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.dataSource.remote.response.todayPrayerTimes.Timings
import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import java.time.LocalTime
import javax.inject.Inject

class PrayerTimesMapper @Inject constructor() : Mapper<Timings, TodayPrayerTimes> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(input: Timings): TodayPrayerTimes {
        return TodayPrayerTimes(
            Fajr = LocalTime.parse(input.Fajr),
            Dhuhr = LocalTime.parse(input.Dhuhr),
            Asr = LocalTime.parse(input.Asr),
            Maghrib = LocalTime.parse(input.Maghrib),
            Isha = LocalTime.parse(input.Isha)
            )
    }
}