package com.example.data.dataSource.remote


import com.example.data.dataSource.remote.mapper.reciters.ReciterMapper
import com.example.data.dataSource.remote.service.RecitersService
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.data.utils.wrapApiResponse
import com.example.domain.entity.reciters.ReciterEntity
import com.example.domain.repo.RecitersRepository
import javax.inject.Inject

class RecitersRepositoryImpl @Inject constructor(
    private val  recitersService: RecitersService
    ) : RecitersRepository {
    override suspend fun getAllReciters():  List<ReciterEntity>  {

        val reciterResponse = wrapApiResponse {recitersService.getAllReciters()}
        return  ReciterMapper.mapToReciterEntityList(reciterResponse.reciters)

    }

}