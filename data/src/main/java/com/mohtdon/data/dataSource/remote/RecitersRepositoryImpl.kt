package com.mohtdon.data.dataSource.remote


import com.mohtdon.data.dataSource.remote.mapper.reciters.ReciterMapper
import com.mohtdon.data.dataSource.remote.service.RecitersService
import com.mohtdon.data.utils.wrapApiResponse
import com.mohtdon.domain.models.reciters.ReciterEntity
import com.mohtdon.domain.repo.RecitersRepository
import javax.inject.Inject

class RecitersRepositoryImpl @Inject constructor(
    private val  recitersService: RecitersService
    ) : RecitersRepository {
    override suspend fun getAllReciters():  List<ReciterEntity>  {

        val reciterResponse = wrapApiResponse {recitersService.getAllReciters()}
        return  ReciterMapper.mapToReciterEntityList(reciterResponse.reciters)

    }

}