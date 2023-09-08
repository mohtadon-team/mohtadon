package com.example.straterproject.ui.fragments.home

sealed class HomeItem(val priority: Int) {


    data class NextSalah(var data: NextSalahUiState) : HomeItem(0)


}
