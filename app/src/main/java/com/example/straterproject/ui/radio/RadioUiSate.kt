package com.example.straterproject.ui.radio

import com.example.domain.entity.radio.RadioEntity

data class RadioUiSate(
    var isTabTitleVisible :Boolean = true,
    var isTabSearchVisible :Boolean = false,
    var searchText : String = "",
    var radioStations : List<RadioEntity> = emptyList(),
    var isLoading : Boolean  = false,
    var isError :Boolean = false ,
    var errorMessage :String = "" ,
    var isDataExist  :Boolean = false
)