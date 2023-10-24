package com.example.data.dataSource.repository.PrayerTimes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.dataSource.remote.mapper.PrayerTimesMapper
import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import com.example.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class PrayerTimesRepositoryImp @Inject constructor(
    private val  prayerTimesService: PrayerTimesService
) : PrayerTimesRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getTodayPrayerTimes(
        date: String, latitude: Double, longitude: Double
    ): TodayPrayerTimes {

         val prayerTimesMapper: PrayerTimesMapper = PrayerTimesMapper()

        var respones =
            prayerTimesService.getTodayPrayerTimes(date, latitude, longitude).body()?.data?.timings

//        if (respones != null) {
            return prayerTimesMapper.map(respones!!)
//        } else {
//            throw Throwable("Failed to fetch prayer times. Response: $respones")
//        }

    }
}