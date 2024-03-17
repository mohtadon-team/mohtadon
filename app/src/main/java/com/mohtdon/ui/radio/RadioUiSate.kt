package com.mohtdon.ui.radio

import com.mohtdon.domain.models.radio.RadioEntity

data class RadioUiSate(
    var isTabTitleVisible :Boolean = true,
    var isTabSearchVisible :Boolean = false,
    var searchText : String = "",
    var radioStations : List<RadioEntity> = emptyList(),
    var isLoading : Boolean  = false,
    var isError :Boolean = false,
    var errorMessage :String = "",
    var isDataExist  :Boolean = false
)