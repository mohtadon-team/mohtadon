package com.example.data.dataSource.remote.mapper

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.data.dataSource.remote.response.monthPrayerTimes.MonthPrayerTimesDto
import com.example.domain.models.prayerTimes.MonthPrayerTimes
import com.example.domain.models.todayPrayerTimes.DayPrayerTimes
import java.time.LocalTime
import javax.inject.Inject

//val Fajr: LocalTime,
//val Dhuhr: LocalTime,
//val Asr: LocalTime,
//val Maghrib: LocalTime,
//val Isha: LocalTime

class MonthPrayerTimesMapper @Inject constructor() : Mapper<MonthPrayerTimesDto, MonthPrayerTimes> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(input: MonthPrayerTimesDto): MonthPrayerTimes {
        var monthPrayertimesList = arrayListOf<DayPrayerTimes>()
//        Log.i("ahmed", "map 1 ")
        input.data.forEach {
//            Log.i("ahmed", "map 2")
            monthPrayertimesList.add(
                DayPrayerTimes(
                    fajr = LocalTime.parse(it.timings.Fajr.split(" ").get(0)),
                    dhuhr = LocalTime.parse(it.timings.Dhuhr.split(" ").get(0)),
                    asr = LocalTime.parse(it.timings.Asr.split(" ").get(0)),
                    maghrib = LocalTime.parse(it.timings.Maghrib.split(" ").get(0)),
                    isha = LocalTime.parse(it.timings.Isha.split(" ").get(0)),
                )
            )
        }
//        Log.i("ahmed", "map 3 ")

        return MonthPrayerTimes(monthPrayertimesList)
    }
}