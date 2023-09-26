package com.example.straterproject.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

//    @Singleton
//    @Provides
//    fun providesRoomDatabase(
//        @ApplicationContext context: Context,
//    ): MusicDataBase =
//        Room.databaseBuilder(context, MusicDataBase::class.java, "DataBase")
//            .build()
//
//    @Singleton
//    @Provides
//    fun provideMusicDao(musicDataBase: MusicDataBase): MusicDao {
//        return musicDataBase.musicDao()
//    }

}