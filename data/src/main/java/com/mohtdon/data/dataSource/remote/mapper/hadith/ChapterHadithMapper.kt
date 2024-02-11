package com.mohtdon.data.dataSource.remote.mapper.hadith

import com.mohtdon.data.dataSource.remote.mapper.Mapper
import com.mohtdon.data.dataSource.remote.response.hadith.chapterHadith.ChapterHadithDto
import com.mohtdon.domain.models.hadith.ChapterHadith
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