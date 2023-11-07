package com.example.domain.models.todayPrayerTimes

import java.time.LocalTime

data class DayPrayerTimes(
    val fajr: LocalTime,
    val dhuhr: LocalTime,
    val asr: LocalTime,
    val maghrib: LocalTime,
    val isha: LocalTime
)
