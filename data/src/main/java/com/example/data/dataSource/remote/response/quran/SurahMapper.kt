package com.example.data.dataSource.remote.response.quran

import com.example.domain.entity.Surah


object SurahMapper {
    fun mapSurahDto(surahDto: SurahDto): List<Surah> {
        return surahDto.data?.map { dataItem ->
            Surah(
                number = dataItem.number,
                name = dataItem.name,
                englishName = dataItem.englishName,
                revelationType = dataItem.revelationType
            )
        } ?: emptyList()
    }
}