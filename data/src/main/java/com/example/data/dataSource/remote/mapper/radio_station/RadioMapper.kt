package com.example.data.dataSource.remote.mapper.radio_station

import com.example.data.dataSource.remote.mapper.Mapper
import com.example.data.dataSource.remote.mapper.reciters.ReciterMapper
import com.example.data.dataSource.remote.response.radoi.Radio
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.ReciterEntity

object RadioMapper :Mapper<Radio , RadioEntity> {

    override fun map(input: Radio): RadioEntity {
       return RadioEntity(
            name = input.name ,
            source = input.url ,
            image = ""
        )
    }

    fun mapToRadioEntityList(radioList: List<Radio>): List<RadioEntity> {
        return radioList.map {
            RadioMapper.map(it)
        }
    }
}