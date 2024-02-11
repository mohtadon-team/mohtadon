package com.mohtdon.domain.entity.reciters

data class ReciterEntity(
    val id : Int ,
    val moshafEnitity: List<MoshafEnitity>,
    val name: String ,
    var isExpandable :Boolean
)