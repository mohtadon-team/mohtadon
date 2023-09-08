package com.example.domain.usecases

import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import com.example.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class GetTodayPrayerTimesUseCase @Inject constructor(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(
        date: String, latitude: Double, longitude: Double
    ): TodayPrayerTimes  = prayerTimesRepository.getTodayPrayerTimes(date, latitude, longitude)

}