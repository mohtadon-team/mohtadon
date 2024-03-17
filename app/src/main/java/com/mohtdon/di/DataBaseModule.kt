package com.mohtdon.di

import android.app.Application
import androidx.room.Room
import com.mohtdon.data.dataSource.local.QuranDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    //provide database
    @Provides
    @Singleton
    fun provideDatabase(application: Application): QuranDatabase {
        return Room.databaseBuilder(application, QuranDatabase::class.java, "quran.db")
            .createFromAsset("quran/databases/quran.db").build()
    }

    //provide Dao
    @Singleton
    @Provides
    fun provideDao(database: QuranDatabase) = database.quranDao()
}