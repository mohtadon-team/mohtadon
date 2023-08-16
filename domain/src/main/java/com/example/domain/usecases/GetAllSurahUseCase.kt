package com.example.domain.usecases

import com.example.domain.repo.SurahRepository
import javax.inject.Inject

class GetAllSurahUseCase @Inject constructor
    (private  val repository: SurahRepository) {
    suspend  fun invoke() = repository.getAllSurah()
}