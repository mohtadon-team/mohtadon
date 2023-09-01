package com.example.data.dataSource.remote.response.quran

import com.example.domain.entity.Surah


object SurahMapper {

    fun mapSurahDto(dto: SurahDto?): List<Surah> {
        println("Mapping SurahDto: $dto")
        return dto?.chapters?.mapNotNull { chapterDto ->
            Surah(
                id = chapterDto?.id ?: 0,
                nameArabic = chapterDto?.name_arabic ,
                revelationPlace = chapterDto?.revelation_place ,
                pages = chapterDto?.pages.orEmpty()
            )
        } ?: emptyList()
    }
}

