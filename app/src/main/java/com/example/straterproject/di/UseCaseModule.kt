package com.example.straterproject.di

import com.example.domain.repo.RecitersRepository
import com.example.domain.repo.SurahRepository
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.domain.usecases.GetAllSurahUseCase
import com.example.domain.usecases.GetSurahAyahsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCas(recitersRepository: RecitersRepository ):GetAllReciterUseCase {
        return GetAllReciterUseCase(recitersRepository)
    }
    @Provides
    fun provideSurahUseCase(surahRepository: SurahRepository):GetAllSurahUseCase{
        return GetAllSurahUseCase(surahRepository)
    }
    @Provides
    fun provideAyahUseCase(surahRepository: SurahRepository):GetSurahAyahsUseCase{
        return GetSurahAyahsUseCase(surahRepository)
    }

}