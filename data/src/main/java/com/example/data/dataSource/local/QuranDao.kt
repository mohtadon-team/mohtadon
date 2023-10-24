package com.example.data.dataSource.local

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.models.Aya


@Dao
interface QuranDao {
    @Query("SELECT * FROM quran WHERE aya_text_emlaey LIKE '%' || :subAya || '%'")
    fun getAyaBySubText(subAya: String): List<Aya>

    @Query("SELECT aya_text FROM quran WHERE id IS :ayaId")
    fun getAyaById(ayaId:Int):String
}