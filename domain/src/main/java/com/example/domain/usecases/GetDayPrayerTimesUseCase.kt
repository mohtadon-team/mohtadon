package com.example.domain.usecases

import com.example.domain.models.todayPrayerTimes.DayPrayerTimes
import com.example.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class GetDayPrayerTimesUseCase @Inject constructor(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes  = prayerTimesRepository.getDayPrayerTimes(date, latitude, longitude)

}