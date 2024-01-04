package com.example.mohtdon.ui.home.uistate

import com.example.domain.models.todayPrayerTimes.DayPrayerTimes
import com.example.mohtdon.ui.home.rvitems.HomeItem

data class HomeUiState(
    val dayPrayerTimes: DayPrayerTimes? = null,
    val nextSalah: HomeItem = HomeItem.NextSalah("", ""),
    val isLoading:Boolean = false,
    val error : Boolean = false,
    val nextSalahName:String  = "",
    val nextSalahTime:String  = "",
    val currentTimeAndNextSalahTimeDifference:String = "",
    val doYouPrayTheLastSalah:String = "",
    val thePercentageBetweenCurrentTimeAndNextSalahTime:String = "",
    val currentDate:String="",
    val randomAya:String = "",
    val randomDuaa:String = ""
    )