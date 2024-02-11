package com.mohtdon.data.dataSource.local

import androidx.room.Dao
import androidx.room.Query
import com.mohtdon.domain.models.quran.PageData
import com.mohtdon.domain.models.quran.Aya


@Dao
interface QuranDao {
    @Query("SELECT * FROM quran WHERE aya_text_emlaey LIKE '%' || :subAya || '%'")
    fun getAyaBySubText(subAya: String): List<Aya>

    @Query("SELECT aya_text FROM quran WHERE id IS :ayaId")
    fun getAyaById(ayaId:Int):String
    @Query("SELECT page as pageNumber, sora_name_ar as soraName, jozz as jozzaName FROM quran WHERE page = :pageNum")
    fun getSoraNameByPage(pageNum: Int): PageData
}