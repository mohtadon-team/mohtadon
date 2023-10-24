package com.example.straterproject.ui.fragments.home

import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes

data class HomeUiState(
    val todayPrayerTimes: TodayPrayerTimes? = null,
    val nextSalah: HomeItem = HomeItem.NextSalah("" , ""),
    val isLoading:Boolean = false,
    val error : Boolean = false,
    val nextSalahName:String  = "",
    val nextSalahTime:String  = "",
    val currentTimeAndNextSalahTimeDifference:String = "" ,
    val doYouPrayTheLastSalah:String = "",
    val thePercentageBetweenCurrentTimeAndNextSalahTime:String = "" ,
    val currentDate:String="" ,
    val randomAya:String = "" ,
    val randomDuaa:String = ""
    )