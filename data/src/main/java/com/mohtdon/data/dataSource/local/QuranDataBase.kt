package com.mohtdon.data.dataSource.local

import com.mohtdon.domain.models.quran.Aya
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Aya::class], version = 1, exportSchema = false)
abstract class QuranDatabase : RoomDatabase() {
    abstract fun quranDao(): QuranDao
}
