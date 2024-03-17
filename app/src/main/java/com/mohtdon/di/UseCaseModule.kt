package com.mohtdon.di

import com.mohtdon.domain.repo.HadithRepository
import com.mohtdon.domain.repo.RadioRepository
import com.mohtdon.domain.repo.RecitersRepository
import com.mohtdon.domain.repo.SurahRepository
import com.mohtdon.domain.usecases.GetAllRadioStation
import com.mohtdon.domain.repo.PrayerTimesRepository
import com.mohtdon.domain.repo.QuranRepository
import com.mohtdon.domain.usecases.GetAllReciterUseCase
import com.mohtdon.domain.usecases.GetAllSurahUseCase
import com.mohtdon.domain.usecases.GetAyaByIdUseCase
import com.mohtdon.domain.usecases.GetSurahAyahsUseCase
import com.mohtdon.domain.usecases.GetDayPrayerTimesUseCase
import com.mohtdon.domain.usecases.GetMonthPrayerTimesUseCase
import com.mohtdon.domain.usecases.GetSpecificBookChapterHadithUseCase
import com.mohtdon.domain.usecases.GetSpecificHadithBookChaptersUseCase
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

    @Provides
    fun provideRadioUseCase(radioRepository: RadioRepository):GetAllRadioStation{
        return GetAllRadioStation(radioRepository)
    }


    @Provides
    fun provideDayPrayerTimesUseCase(
         prayerTimesRepository: PrayerTimesRepository
    ):GetDayPrayerTimesUseCase{
        return GetDayPrayerTimesUseCase(prayerTimesRepository)
    }

    @Provides
    fun provideMonthPrayerTimesUseCase(
        prayerTimesRepository: PrayerTimesRepository
    ):GetMonthPrayerTimesUseCase{
        return GetMonthPrayerTimesUseCase(prayerTimesRepository)
    }



    @Provides
    fun provideGetAyaByIdUseCas(quranRepository: QuranRepository):GetAyaByIdUseCase {
        return GetAyaByIdUseCase(quranRepository)
    }
    @Provides
    fun provideHadithBookChaptersUseCase(
        hadithRepository: HadithRepository
    ):GetSpecificHadithBookChaptersUseCase{
        return GetSpecificHadithBookChaptersUseCase(hadithRepository)
    }

    @Provides
    fun provideChapterHadithUseCase(
        hadithRepository: HadithRepository
    ): GetSpecificBookChapterHadithUseCase {
        return GetSpecificBookChapterHadithUseCase(hadithRepository)
    }
}