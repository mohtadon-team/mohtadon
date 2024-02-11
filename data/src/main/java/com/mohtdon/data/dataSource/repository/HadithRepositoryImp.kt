package com.mohtdon.data.dataSource.repository

import com.mohtdon.data.dataSource.remote.mapper.hadith.ChapterHadithMapper
import com.mohtdon.data.dataSource.remote.mapper.hadith.HadithBookChaptersMapper
import com.mohtdon.data.dataSource.remote.service.HadithService
import com.mohtdon.domain.models.hadith.ChapterHadith
import com.mohtdon.domain.models.hadith.HadithBookChapters
import com.mohtdon.domain.repo.HadithRepository
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