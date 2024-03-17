package com.mohtdon.data.dataSource.remote.mapper.reciters

import com.mohtdon.data.dataSource.remote.mapper.Mapper
import com.mohtdon.data.dataSource.remote.response.reciters.Reciter
import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.domain.models.reciters.ReciterEntity


object ReciterMapper : Mapper<Reciter, ReciterEntity> {

    override fun map(input: Reciter): ReciterEntity {
        return ReciterEntity(
            id = input.id,
            moshafEnitity = input.moshaf.map { moshaf ->
                MoshafEnitity(
                    reciterName = input.name,
                    id = moshaf.id,
                    moshafType = moshaf.moshaf_type,
                    moshafName = moshaf.name,
                    server = moshaf.server,
                    surah_list = moshaf.surah_list,
                    surah_total = moshaf.surah_total
                )
            },
            name = input.name,
            isExpandable = false
        )
    }

    fun mapToReciterEntityList(reciterList: List<Reciter>): List<ReciterEntity> {
        return reciterList.map {
            map(it)
        }
    }

}