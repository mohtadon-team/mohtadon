package com.example.domain.models.prayerTimes

import com.example.domain.models.todayPrayerTimes.DayPrayerTimes

data class MonthPrayerTimes(
    val data: List<DayPrayerTimes>
)