package com.mohtdon.ui.reciters.surahs

import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.domain.models.reciters.SuraEntity

data class SurahUiState (
    var isTabTitleVisible :Boolean = true,
    var isTabSearchVisible :Boolean = false,
    var searchText : String = "",
    var surahList : List<SuraEntity?> = emptyList(),
    var reciterInfo : String = "",
    var moshaf : MoshafEnitity =  MoshafEnitity()
)