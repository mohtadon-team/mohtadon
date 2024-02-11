package com.mohtdon.domain.models.prayerTimes

import com.mohtdon.domain.models.todayPrayerTimes.DayPrayerTimes

data class MonthPrayerTimes(
    val data: List<DayPrayerTimes>
)