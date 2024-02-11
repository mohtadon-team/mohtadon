package com.mohtdon.data.dataSource.remote.response.monthPrayerTimes

data class MonthPrayerTimesDto(
    val code: Int,
    val `data`: List<Data>,
    val status: String
)