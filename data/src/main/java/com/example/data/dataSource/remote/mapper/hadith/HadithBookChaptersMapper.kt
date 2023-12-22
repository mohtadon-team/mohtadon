package com.example.data.dataSource.remote.mapper.hadith

import com.example.data.dataSource.remote.mapper.Mapper
import com.example.data.dataSource.remote.response.hadith.chapters.BookChaptersDto
import com.example.domain.models.hadith.HadithBookChapters
import javax.inject.Inject

class HadithBookChaptersMapper @Inject constructor() : Mapper<BookChaptersDto, HadithBookChapters> {
    override fun map(input: BookChaptersDto): HadithBookChapters {
        val bookChaptersResponse = input.chapters
        val hadithBookChaptersList = ArrayList<String>()
        bookChaptersResponse.forEach {
            hadithBookChaptersList.add(it.chapterArabic)
        }

        return HadithBookChapters(hadithBookChaptersList)
    }
}