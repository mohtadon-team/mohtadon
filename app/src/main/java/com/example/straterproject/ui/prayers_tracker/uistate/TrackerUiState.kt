package com.example.straterproject.ui.prayers_tracker.uistate

import com.example.straterproject.ui.prayers_tracker.models.Salah

data class TrackerUiState(
    val currentDate:String = "",
    val todayPrayersProgress:Int = 0 ,
    val dayPrayers:List<Salah>  = mutableListOf()
)
