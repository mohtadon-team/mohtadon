package com.mohtdon.data.dataSource.remote

import com.mohtdon.data.dataSource.remote.mapper.radio_station.RadioMapper
 import com.mohtdon.data.dataSource.remote.service.RadioService
import com.mohtdon.data.utils.wrapApiResponse
import com.mohtdon.domain.models.radio.RadioEntity
import com.mohtdon.domain.repo.RadioRepository
import javax.inject.Inject

class RadioRepositoryImpl @Inject constructor (private val radioService: RadioService) :RadioRepository{
    override suspend fun getAllRadioStation(): List<RadioEntity> {
        val radioResponse = wrapApiResponse{radioService.getAllRadioStation()}
        return  RadioMapper.mapToRadioEntityList(radioResponse.radios)
    }


}