package com.mohtdon.domain.models.prayerTimes

import java.time.LocalTime

data class DayPrayerTimes(
    val fajr: LocalTime,
    val dhuhr: LocalTime,
    val asr: LocalTime,
    val maghrib: LocalTime,
    val isha: LocalTime
)
