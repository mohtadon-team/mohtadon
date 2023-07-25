package com.example.domain.usecases

import com.example.domain.repo.QuranRepository
import javax.inject.Inject

class GetAllReciterUseCase @Inject constructor(
   private val quranRepository: QuranRepository
) {
    suspend  fun invoke() = quranRepository.getAllReciter()
}