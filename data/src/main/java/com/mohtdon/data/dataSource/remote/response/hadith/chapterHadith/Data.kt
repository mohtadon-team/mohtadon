package com.mohtdon.data.dataSource.remote.response.hadith.chapterHadith

data class Data(
    val book: Book,
    val bookSlug: String,
    val chapter: Chapter,
    val chapterId: String,
    val englishNarrator: String,
    val hadithArabic: String,
    val hadithEnglish: String,
    val hadithNumber: String,
    val hadithUrdu: String,
    val headingArabic: String,
    val headingEnglish: String,
    val headingUrdu: String,
    val id: Int,
    val status: String,
    val urduNarrator: String,
    val volume: String
)