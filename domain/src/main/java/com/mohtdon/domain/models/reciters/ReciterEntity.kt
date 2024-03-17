package com.mohtdon.domain.models.reciters

import com.mohtdon.domain.models.reciters.MoshafEnitity

data class ReciterEntity(
    val id : Int,
    val moshafEnitity: List<MoshafEnitity>,
    val name: String,
    var isExpandable :Boolean
)