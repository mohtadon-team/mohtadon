package com.mohtdon.di


import com.mohtdon.data.dataSource.local.QuranDao
import com.mohtdon.data.dataSource.local.QuranRepositoryImpl
import com.mohtdon.data.dataSource.remote.RadioRepositoryImpl
import com.mohtdon.data.dataSource.remote.RecitersRepositoryImpl
import com.mohtdon.data.dataSource.remote.SurahRepositoryImpl
import com.mohtdon.data.dataSource.remote.service.HadithService
import com.mohtdon.data.dataSource.remote.service.QuranApiService
import com.mohtdon.data.dataSource.remote.service.RadioService
import com.mohtdon.data.dataSource.remote.service.RecitersService
import com.mohtdon.domain.repo.RadioRepository
import com.mohtdon.domain.repo.RecitersRepository
import com.mohtdon.domain.repo.SurahRepository

import com.mohtdon.data.dataSource.remote.service.PrayerTimesService
import com.mohtdon.data.dataSource.repository.HadithRepositoryImp
import com.mohtdon.data.dataSource.repository.PrayerTimes.PrayerTimesRepositoryImp
import com.mohtdon.domain.repo.HadithRepository
import com.mohtdon.domain.repo.PrayerTimesRepository
import com.mohtdon.domain.repo.QuranRepository


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

    @Provides
    fun provideRadioRepository(radioService: RadioService):RadioRepository{
        return RadioRepositoryImpl(radioService)
    }

    @Provides
    fun providePrayerTimesRepository(
        prayerTimesService: PrayerTimesService): PrayerTimesRepository{
        return PrayerTimesRepositoryImp(  prayerTimesService )
    }
    @Provides
    fun provideQuranSearchRepository(
        quranDao: QuranDao
    ):QuranRepository{
        return QuranRepositoryImpl(quranDao)
    }


    @Provides
    fun provideHadithRepository(
        hadithService: HadithService): HadithRepository{
        return HadithRepositoryImp(  hadithService )
    }
}

//     fun bindPrayerTimesRepository(
//       prayerTimesService: PrayerTimesService): PrayerTimesRepository{
//         return PrayerTimesRepositoryImp(  prayerTimesService )
//     }

//    @Provides
//     fun bindQuranRepository(
//        apiService: ApiService ,
//    ): QuranRepository{
//         return QuranRepositoryImpl(apiService)
//     }

//    @Provides
//    fun provideRadioRepository(radioService: RadioService):RadioRepository {
//        return

