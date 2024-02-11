package com.mohtdon.data.dataSource.remote.response.reciters

data class Reciter(
    val id: Int,
    val letter: String,
    val moshaf: List<Moshaf>,
    val name: String
)