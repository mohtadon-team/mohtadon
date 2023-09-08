package com.example.straterproject.ui.fragments.home

data class HomeUiState(

    val nextSalahState: HomeItem = HomeItem.NextSalah(NextSalahUiState("" , 0)) ,
    val isLoading:Boolean = false,
    val error : List<String> = emptyList()


    )