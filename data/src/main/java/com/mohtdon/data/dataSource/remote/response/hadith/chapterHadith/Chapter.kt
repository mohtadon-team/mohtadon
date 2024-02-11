package com.mohtdon.data.dataSource.remote.response.hadith.chapterHadith

data class Chapter(
    val bookSlug: String,
    val chapterArabic: String,
    val chapterEnglish: String,
    val chapterNumber: String,
    val chapterUrdu: String,
    val id: Int
)