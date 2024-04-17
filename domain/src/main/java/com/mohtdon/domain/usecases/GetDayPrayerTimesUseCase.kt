package com.mohtdon.domain.usecases

import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes
import com.mohtdon.domain.repo.PrayerTimesRepository
import javax.inject.Inject

class GetDayPrayerTimesUseCase @Inject constructor(
    private val prayerTimesRepository: PrayerTimesRepository
) {
    suspend operator fun invoke(
        date: String?, latitude: Double?, longitude: Double?
    ): DayPrayerTimes? {
        // Check if any of the required parameters are null
        if (date == null || latitude == null || longitude == null) {
            return null
        }
        // Invoke the repository method only if all parameters are non-null
        return prayerTimesRepository.getDayPrayerTimes(date, latitude, longitude)
    }

}