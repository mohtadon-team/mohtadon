package com.mohtdon.domain.usecases

import com.mohtdon.domain.repo.QuranRepository
import javax.inject.Inject

class GetAyaByIdUseCase @Inject constructor(
    private val quranRepository: QuranRepository
) {
    suspend fun invoke(ayaId:Int) = quranRepository.getAyaId(ayaId)
}