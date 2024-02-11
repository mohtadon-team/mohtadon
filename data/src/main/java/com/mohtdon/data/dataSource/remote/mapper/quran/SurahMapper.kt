package com.mohtdon.data.dataSource.remote.mapper.quran

import com.mohtdon.data.dataSource.remote.response.quran.SurahDto
import com.mohtdon.domain.models.quran.Surah


object SurahMapper {

    fun mapSurahDto(dto: SurahDto?): List<Surah> {
        println("Mapping SurahDto: $dto")
        return dto?.chapters?.mapNotNull { chapterDto ->
            Surah(
                id = chapterDto?.id ?: 0,
                nameArabic = chapterDto?.name_arabic ,
                pages = chapterDto?.pages.orEmpty(),
                revelationPlace = chapterDto?.revelation_place ,
                verses_count = chapterDto?.verses_count?:0
            )
        } ?: emptyList()
    }
}

