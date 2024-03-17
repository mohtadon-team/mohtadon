package com.mohtdon.data.dataSource.remote.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.mohtdon.data.dataSource.remote.response.DayPrayerTimes.DayPrayerTimesDto
import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes
import java.time.LocalTime
import javax.inject.Inject

class DayPrayerTimesMapper @Inject constructor() : Mapper<DayPrayerTimesDto, DayPrayerTimes> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(input: DayPrayerTimesDto): DayPrayerTimes {

        val prayerTimings = input.data.timings
        return DayPrayerTimes(
            fajr = LocalTime.parse(prayerTimings.Fajr),
            dhuhr = LocalTime.parse(prayerTimings.Dhuhr),
            asr = LocalTime.parse(prayerTimings.Asr),
            maghrib = LocalTime.parse(prayerTimings.Maghrib),
            isha = LocalTime.parse(prayerTimings.Isha)
            )
    }
}