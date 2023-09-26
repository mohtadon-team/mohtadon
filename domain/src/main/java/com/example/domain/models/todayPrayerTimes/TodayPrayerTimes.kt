package com.example.domain.models.todayPrayerTimes

import java.time.LocalTime

data class TodayPrayerTimes(
    val Fajr: LocalTime,
    val Dhuhr: LocalTime,
    val Asr: LocalTime,
    val Maghrib: LocalTime,
    val Isha: LocalTime
)
