package com.mohtdon.domain.usecases

import com.mohtdon.domain.repo.RadioRepository
import javax.inject.Inject


class GetAllRadioStation @Inject constructor(
    private val radioRepository: RadioRepository
) {
    suspend operator fun invoke() = radioRepository.getAllRadioStation()
}