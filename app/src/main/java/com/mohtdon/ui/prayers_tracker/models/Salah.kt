package com.mohtdon.mohtdon.ui.prayers_tracker.models

data class Salah(
    val name:String = "" ,
    var isPerformed:Boolean = false ,
    val time:String = "" ,
    val isItToday:Boolean = false
)
