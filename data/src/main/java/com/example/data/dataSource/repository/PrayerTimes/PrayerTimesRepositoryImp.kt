package com.example.data.dataSource.repository.PrayerTimes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.dataSource.remote.mapper.DayPrayerTimesMapper
import com.example.data.dataSource.remote.mapper.MonthPrayerTimesMapper
import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.domain.models.prayerTimes.MonthPrayerTimes
import com.example.domain.models.todayPrayerTimes.DayPrayerTimes
import com.example.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class PrayerTimesRepositoryImp @Inject constructor(
    private val prayerTimesService: PrayerTimesService
) : PrayerTimesRepository {

    lateinit var dayPrayerTimesMapper: DayPrayerTimesMapper
    lateinit var monthPrayerTimesMapper: MonthPrayerTimesMapper

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes {


        val response = prayerTimesService.getDayPrayerTimes(date, latitude, longitude).body()
        dayPrayerTimesMapper = DayPrayerTimesMapper()
        if (response != null) {
            dayPrayerTimesMapper = DayPrayerTimesMapper()
            return dayPrayerTimesMapper.map(response)
        } else {
            throw RuntimeException("Prayer times response is null.")
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getMonthPrayerTimes(
        month: Int, year: Int, latitude: Double, longitude: Double
    ): MonthPrayerTimes {

        monthPrayerTimesMapper = MonthPrayerTimesMapper()

//        Log.i("ahmed", "getMonthPrayerTimes 1 ")
        val response = prayerTimesService.getMonthPrayerTimes(
            month, year, latitude, longitude
        ).body()
//            .body()
//        Log.i("ahmed", "getMonthPrayerTimes 2 ")
//        Log.i("ahmed", response.body()?.code.toString())
        return monthPrayerTimesMapper.map(response!!)
    }
}