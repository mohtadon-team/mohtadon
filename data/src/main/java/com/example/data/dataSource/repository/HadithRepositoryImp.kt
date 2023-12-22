package com.example.data.dataSource.repository

import android.util.Log
import com.example.data.dataSource.remote.mapper.hadith.HadithBookChaptersMapper
import com.example.data.dataSource.remote.service.HadithService
import com.example.domain.models.hadith.HadithBookChapters
import com.example.domain.repo.HadithRepository
import javax.inject.Inject

class HadithRepositoryImp  @Inject constructor(
    private val hadithService: HadithService
) : HadithRepository {
    override suspend fun getSpecificHadithBookChapters(bookName: String): HadithBookChapters? {
        try {
            val response = hadithService.getHadithBookChapters().body()
            Log.i("ahmed", response.toString())

        }catch (e:Exception){
            Log.i("ahmed", e.message.toString())
        }
//        val response = hadithService.getHadithBookChapters(bookName).body()
        val hadithBookChaptersMapper = HadithBookChaptersMapper()

//        if (respones != null) {
//        return hadithBookChaptersMapper.map(response!!)
        return null
    }
}