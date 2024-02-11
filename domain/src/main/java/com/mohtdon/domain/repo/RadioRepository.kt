package com.mohtdon.domain.repo

import com.mohtdon.domain.entity.radio.RadioEntity

interface RadioRepository {
    suspend fun getAllRadioStation() : List<RadioEntity>?
}