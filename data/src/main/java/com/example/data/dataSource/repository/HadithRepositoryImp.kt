package com.example.data.dataSource.repository

import com.example.data.dataSource.remote.mapper.hadith.ChapterHadithMapper
import com.example.data.dataSource.remote.mapper.hadith.HadithBookChaptersMapper
import com.example.data.dataSource.remote.service.HadithService
import com.example.domain.models.hadith.ChapterHadith
import com.example.domain.models.hadith.HadithBookChapters
import com.example.domain.repo.HadithRepository
import javax.inject.Inject

class HadithRepositoryImp @Inject constructor(
    private val hadithService: HadithService
) : HadithRepository {
    override suspend fun getSpecificHadithBookChapters(bookName: String): HadithBookChapters {
        val response = hadithService.getHadithBookChapters(bookName).body()
        val hadithBookChaptersMapper = HadithBookChaptersMapper()
        return hadithBookChaptersMapper.map(response!!)
    }

    override suspend fun getSpecificChapterHadith(
        bookName: String,
        chapterNumber: Int
    ): ChapterHadith {
        return ChapterHadithMapper().map(
            hadithService.getChapterHadith(
                bookName = bookName,
                chapterNumber = chapterNumber
            ).body()!!
        )
    }
}