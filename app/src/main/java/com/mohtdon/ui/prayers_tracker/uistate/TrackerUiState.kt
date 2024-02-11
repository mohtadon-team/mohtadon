package com.mohtdon.mohtdon.ui.prayers_tracker.uistate

import com.mohtdon.mohtdon.ui.prayers_tracker.models.Salah

data class TrackerUiState(
    val currentDate:String = "",
    val todayPrayersProgress:Int = 0 ,
    val dayPrayers:List<Salah>  = mutableListOf()
)
