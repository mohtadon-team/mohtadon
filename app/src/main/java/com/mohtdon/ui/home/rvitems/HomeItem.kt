package com.mohtdon.ui.home.rvitems

sealed class HomeItem(val priority: Int) {

    data class NextSalah(var nextSalahName: String, var nextSalahTime: String) : HomeItem(0)


}
