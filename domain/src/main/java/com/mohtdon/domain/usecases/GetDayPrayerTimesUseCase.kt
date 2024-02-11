package com.mohtdon.domain.usecases

import com.mohtdon.domain.models.todayPrayerTimes.DayPrayerTimes
import com.mohtdon.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class GetDayPrayerTimesUseCase @Inject constructor(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(
        date: String, latitude: Double, longitude: Double
    ): DayPrayerTimes  = prayerTimesRepository.getDayPrayerTimes(date, latitude, longitude)

}