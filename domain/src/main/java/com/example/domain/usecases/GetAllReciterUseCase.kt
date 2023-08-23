package com.example.domain.usecases

import com.example.domain.repo.RecitersRepository
import javax.inject.Inject

class GetAllReciterUseCase @Inject constructor(
   private val recitersRepository: RecitersRepository
) {
    suspend operator fun invoke() = recitersRepository.getAllReciters()
}