package com.mohtdon.domain.repo

import com.mohtdon.domain.models.radio.RadioEntity

interface RadioRepository {
    suspend fun getAllRadioStation() : List<RadioEntity>?
}