package com.example.straterproject.ui.prayers_tracker

import java.time.LocalDate

data class TrackerUiState(
    val currentDate:String = "",
    val todayPrayersProgress:Int = 0 ,
    val dayPrayers:List<Salah>  = mutableListOf()
)
