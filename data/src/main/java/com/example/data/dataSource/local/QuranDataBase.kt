package com.example.data.dataSource.local

import com.example.domain.models.quran.Aya
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.models.quran.PageData

@Database(entities = [Aya::class,PageData::class], version = 1, exportSchema = false)
abstract class QuranDatabase : RoomDatabase() {
    abstract fun quranDao(): QuranDao
}
