package com.example.data.dataSource.remote


import com.example.data.dataSource.remote.service.QuranApiService
import com.example.data.dataSource.remote.response.quran.SurahDto
import com.example.data.dataSource.remote.response.quran.SurahMapper


import com.example.domain.entity.Surah

import com.example.domain.repo.SurahRepository
import javax.inject.Inject

class SurahRepositoryImpl @Inject constructor(
    private val apiService: QuranApiService
) : SurahRepository {
    override suspend fun getAllSurah(): List<Surah> {
        val surahDto: SurahDto = apiService.getAllSurah()
        return SurahMapper.mapSurahDto(surahDto)
    }


}


