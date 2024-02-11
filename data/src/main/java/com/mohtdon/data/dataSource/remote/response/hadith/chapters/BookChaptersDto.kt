package com.mohtdon.data.dataSource.remote.response.hadith.chapters

data class BookChaptersDto(
    val chapters: List<Chapter>,
    val message: String,
    val status: Int
)