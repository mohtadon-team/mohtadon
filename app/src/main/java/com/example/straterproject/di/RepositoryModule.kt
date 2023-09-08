package com.example.straterproject.di


import com.example.data.dataSource.remote.ApiService
import com.example.data.dataSource.remote.QuranRepositoryImpl
import com.example.data.dataSource.remote.mapper.PrayerTimesMapper
import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.data.dataSource.repository.PrayerTimes.PrayerTimesRepositoryImp
import com.example.domain.repo.PrayerTimesRepository
import com.example.domain.repo.QuranRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

//
//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
////    @ViewModelScoped
//    @Provides
//     fun bindQuranRepository(
//        apiService: ApiService ,
//    ): QuranRepository{
//         return QuranRepositoryImpl(apiService)
//     }
////    @ViewModelScoped
//    @Provides
//     fun bindPrayerTimesRepository(
//       prayerTimesService: PrayerTimesService): PrayerTimesRepository{
//         return PrayerTimesRepositoryImp(  prayerTimesService )
//     }
//
//
//}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Provides
//    fun provideQuranRepository(recitersService: RecitersService): RecitersRepository  {
//        return  RecitersRepositoryImpl(recitersService)
//    }


    @Provides
     fun bindPrayerTimesRepository(
       prayerTimesService: PrayerTimesService): PrayerTimesRepository{
         return PrayerTimesRepositoryImp(  prayerTimesService )
     }

    @Provides
     fun bindQuranRepository(
        apiService: ApiService ,
    ): QuranRepository{
         return QuranRepositoryImpl(apiService)
     }

//    @Provides
//    fun provideRadioRepository(radioService: RadioService):RadioRepository {
//        return

    }