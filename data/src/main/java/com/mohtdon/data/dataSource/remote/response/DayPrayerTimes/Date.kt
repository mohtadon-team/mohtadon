package com.mohtdon.data.dataSource.remote.response.DayPrayerTimes

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)