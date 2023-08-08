package com.example.straterproject.di


import com.example.data.dataSource.remote.ApiService
import com.example.data.dataSource.remote.QuranRepositoryImpl
import com.example.domain.repo.QuranRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideQuranRepository( apiService: ApiService): QuranRepository  {
        return  QuranRepositoryImpl(apiService)
    }
}
