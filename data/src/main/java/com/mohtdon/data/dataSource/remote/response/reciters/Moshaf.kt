package com.mohtdon.data.dataSource.remote.response.reciters

data class Moshaf(
    val id: Int,
    val moshaf_type: Int,
    val name: String,
    val server: String,
    val surah_list: String,
    val surah_total: Int
)