package com.example.data.dataSource.remote.mapper.hadith

import com.example.data.dataSource.remote.mapper.Mapper
import com.example.data.dataSource.remote.response.hadith.chapterHadith.ChapterHadithDto
import com.example.data.dataSource.remote.response.hadith.chapters.BookChaptersDto
import com.example.domain.models.hadith.ChapterHadith
import com.example.domain.models.hadith.HadithBookChapters
import javax.inject.Inject

class ChapterHadithMapper @Inject constructor() : Mapper<ChapterHadithDto, ChapterHadith> {
    override fun map(input: ChapterHadithDto): ChapterHadith {
        val chapterHadithList = input.hadiths.data
        val chaptersList = ArrayList<String>()
        chapterHadithList.forEach {
            chaptersList.add(it.hadithArabic)
        }

        return ChapterHadith(chaptersList)
    }
}