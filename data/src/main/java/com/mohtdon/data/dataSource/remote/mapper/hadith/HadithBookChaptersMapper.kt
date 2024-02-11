package com.mohtdon.data.dataSource.remote.mapper.hadith

import com.mohtdon.data.dataSource.remote.mapper.Mapper
import com.mohtdon.data.dataSource.remote.response.hadith.chapters.BookChaptersDto
import com.mohtdon.domain.models.hadith.HadithBookChapters
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