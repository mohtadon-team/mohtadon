package com.mohtdon.data.dataSource.remote.mapper.radio_station

import com.mohtdon.data.dataSource.remote.mapper.Mapper
import com.mohtdon.data.dataSource.remote.response.radoi.Radio
import com.mohtdon.domain.models.radio.RadioEntity

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