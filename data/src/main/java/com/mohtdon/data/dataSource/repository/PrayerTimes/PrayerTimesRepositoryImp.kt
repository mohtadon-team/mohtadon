package com.mohtdon.data.dataSource.repository.PrayerTimes

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.mohtdon.data.dataSource.remote.mapper.DayPrayerTimesMapper
import com.mohtdon.data.dataSource.remote.mapper.MonthPrayerTimesMapper
import com.mohtdon.data.dataSource.remote.service.PrayerTimesService
import com.mohtdon.domain.models.prayerTimes.MonthPrayerTimes
import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes
import com.mohtdon.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class PrayerTimesRepositoryImp @Inject constructor(
    private val prayerTimesService: PrayerTimesService
) : PrayerTimesRepository {

    private val dayPrayerTimesMapper = DayPrayerTimesMapper()
    private val monthPrayerTimesMapper = MonthPrayerTimesMapper()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes? {

        try {
            // Code that may throw an exception
            val response = prayerTimesService.getDayPrayerTimes(date, latitude, longitude).body()
                return response?.let { dayPrayerTimesMapper.map(it) }
        } catch (e: Exception) {
            // Handle the exception
            Log.e("error", "An error occurred: ${e.message}", e)
            // Optionally, return a default or empty value
            return null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getMonthPrayerTimes(
        month: Int, year: Int, latitude: Double, longitude: Double
    ): MonthPrayerTimes {
        val response = prayerTimesService.getMonthPrayerTimes(
            month, year, latitude, longitude
        ).body()
        return monthPrayerTimesMapper.map(response!!)
    }
}