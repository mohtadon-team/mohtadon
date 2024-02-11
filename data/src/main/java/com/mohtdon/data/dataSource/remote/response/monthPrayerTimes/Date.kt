package com.mohtdon.data.dataSource.remote.response.monthPrayerTimes

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)