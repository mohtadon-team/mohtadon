package com.mohtdon.domain.usecases

import com.mohtdon.domain.repo.HadithRepository
import javax.inject.Inject

class GetSpecificBookChapterHadithUseCase @Inject constructor(
    private val hadithRepository: HadithRepository
) {
    suspend  fun invoke(bookName: String , chapterNumber:Int ) =
        hadithRepository.getSpecificChapterHadith(bookName , chapterNumber )

}