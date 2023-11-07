package com.example.data.dataSource.local
import com.example.domain.models.Aya
import com.example.domain.repo.QuranRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private val quranDao: QuranDao
) : QuranRepository {

    override suspend fun getAyaBySubText(subAya: String): List<Aya> {
        return withContext(Dispatchers.IO) {
            quranDao.getAyaBySubText(subAya)
        }
    }

    override suspend fun getAyaId(ayaId: Int): String {
        return withContext(Dispatchers.IO){
            quranDao.getAyaById(ayaId)
        }
    }
}