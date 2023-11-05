package com.example.domain.usecases

import com.example.domain.models.prayerTimes.MonthPrayerTimes
import com.example.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class GetMonthPrayerTimesUseCase @Inject constructor(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(
        month: Int, year: Int, latitude: Double, longitude: Double
    ): MonthPrayerTimes = prayerTimesRepository.getMonthPrayerTimes(
        month, year, latitude, longitude
    )

}