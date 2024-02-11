package com.mohtdon.data.dataSource.remote


import com.mohtdon.data.dataSource.remote.service.QuranApiService
import com.mohtdon.data.dataSource.remote.response.quran.SurahDto
import com.mohtdon.data.dataSource.remote.mapper.quran.SurahMapper


import com.mohtdon.domain.models.quran.Surah

import com.mohtdon.domain.repo.SurahRepository
import javax.inject.Inject

class SurahRepositoryImpl @Inject constructor(
    private val apiService: QuranApiService
) : SurahRepository {
    override suspend fun getAllSurah(): List<Surah> {
        val surahDto: SurahDto = apiService.getAllSurah()
        return SurahMapper.mapSurahDto(surahDto)
    }


}


