package com.example.domain.usecases

import com.example.domain.repo.QuranRepository
import javax.inject.Inject

class GetAyaByIdUseCase @Inject constructor(
    private val quranRepository: QuranRepository
) {
    suspend fun invoke(ayaId:Int) = quranRepository.getAyaId(ayaId)
}