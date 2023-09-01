package com.example.domain.repo

import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.ReciterEntity

interface RadioRepository {
    suspend fun getAllRadioStation() : List<RadioEntity>?
}