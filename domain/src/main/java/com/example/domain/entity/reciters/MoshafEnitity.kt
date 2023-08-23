package com.example.domain.entity.reciters

data class MoshafEnitity(
    val reciterName :String ,
    val id :Int ,
    val moshaf_type: Int,
    val name: String,
    val server: String,
    val surah_list: String,
    val surah_total: Int
)