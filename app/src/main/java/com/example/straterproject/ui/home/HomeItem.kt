package com.example.straterproject.ui.home

sealed class HomeItem(val priority: Int) {

    data class NextSalah(var nextSalahName: String, var nextSalahTime: String) : HomeItem(0)


}
