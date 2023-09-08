package com.example.straterproject.di


import com.example.data.dataSource.remote.RadioRepositoryImpl
import com.example.data.dataSource.remote.RecitersRepositoryImpl
import com.example.data.dataSource.remote.SurahRepositoryImpl
import com.example.data.dataSource.remote.endPoint.QuranApiService
import com.example.data.dataSource.remote.service.RadioService
import com.example.data.dataSource.remote.service.RecitersService
import com.example.domain.repo.RadioRepository
import com.example.domain.repo.RecitersRepository
import com.example.domain.repo.SurahRepository

import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.data.dataSource.repository.PrayerTimes.PrayerTimesRepositoryImp
import com.example.domain.repo.PrayerTimesRepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Provides
    fun provideRadioRepository(radioService: RadioService):RadioRepository{
        return RadioRepositoryImpl(radioService)
    }
}

     fun bindPrayerTimesRepository(
       prayerTimesService: PrayerTimesService): PrayerTimesRepository{
         return PrayerTimesRepositoryImp(  prayerTimesService )
     }

//    @Provides
//     fun bindQuranRepository(
//        apiService: ApiService ,
//    ): QuranRepository{
//         return QuranRepositoryImpl(apiService)
//     }

//    @Provides
//    fun provideRadioRepository(radioService: RadioService):RadioRepository {
//        return

