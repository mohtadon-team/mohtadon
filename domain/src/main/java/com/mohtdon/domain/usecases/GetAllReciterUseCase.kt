package com.mohtdon.domain.usecases

import com.mohtdon.domain.repo.RecitersRepository
import javax.inject.Inject

class GetAllReciterUseCase @Inject constructor(
   private val recitersRepository: RecitersRepository
) {
    suspend operator fun invoke() = recitersRepository.getAllReciters()
}