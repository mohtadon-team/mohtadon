package com.example.straterproject.di


import com.example.data.dataSource.remote.RecitersRepositoryImpl
import com.example.data.dataSource.remote.SurahRepositoryImpl
import com.example.data.dataSource.remote.endPoint.QuranApiService
import com.example.data.dataSource.remote.service.RecitersService
import com.example.domain.repo.RecitersRepository
import com.example.domain.repo.SurahRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideQuranRepository(recitersService: RecitersService): RecitersRepository  {
        return  RecitersRepositoryImpl(recitersService)
    }
    @Provides
    fun provideSurahRepository(quranApiService: QuranApiService):SurahRepository{
        return SurahRepositoryImpl(quranApiService)
    }
}
