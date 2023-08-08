package com.example.data.dataSource.remote.response.todayPrayerTimes

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)