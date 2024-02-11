package com.mohtdon.mohtdon.ui.reciters.surahs

import com.mohtdon.domain.entity.reciters.MoshafEnitity
import com.mohtdon.domain.entity.reciters.SuraEntity

data class SurahUiState (
    var isTabTitleVisible :Boolean = true ,
    var isTabSearchVisible :Boolean = false ,
    var searchText : String = "" ,
    var surahList : List<SuraEntity?> = emptyList(),
    var reciterInfo : String = "",
    var moshaf : MoshafEnitity =  MoshafEnitity()
)