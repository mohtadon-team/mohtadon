package com.mohtdon.domain.usecases

import com.mohtdon.domain.repo.SurahRepository
import javax.inject.Inject

class GetAllSurahUseCase @Inject constructor
    (private  val repository: SurahRepository) {
    suspend  fun invoke() = repository.getAllSurah()
}