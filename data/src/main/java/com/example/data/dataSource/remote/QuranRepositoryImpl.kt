package com.example.data.dataSource.remote

import com.example.data.dataSource.remote.endPoint.ApiService
import com.example.domain.repo.QuranRepository
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ) : QuranRepository {
    override suspend fun getAllReciter() = apiService.getData()
}