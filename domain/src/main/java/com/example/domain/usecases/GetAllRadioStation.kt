package com.example.domain.usecases

import com.example.domain.repo.RadioRepository
import com.example.domain.repo.RecitersRepository
import javax.inject.Inject


class GetAllRadioStation @Inject constructor(
    private val radioRepository: RadioRepository
) {
    suspend operator fun invoke() = radioRepository.getAllRadioStation()
}