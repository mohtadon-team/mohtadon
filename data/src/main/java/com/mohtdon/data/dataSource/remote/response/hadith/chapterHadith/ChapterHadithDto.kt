package com.mohtdon.data.dataSource.remote.response.hadith.chapterHadith

data class ChapterHadithDto(
    val hadiths: Hadiths,
    val message: String,
    val status: Int
)