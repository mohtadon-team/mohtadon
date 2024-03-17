package com.mohtdon.di


import com.mohtdon.data.dataSource.remote.service.HadithService
import com.mohtdon.data.dataSource.remote.service.QuranApiService
import com.mohtdon.data.dataSource.remote.service.PrayerTimesService
import com.mohtdon.data.dataSource.remote.service.RadioService
import com.mohtdon.data.dataSource.remote.service.RecitersService
import com.mohtdon.mohtdon.BuildConfig.PRAYER_TIMES_BASE_URL
import com.mohtdon.utilities.RADIO_BASE_URL
import com.mohtdon.utilities.RECITERS_BASE_URL
import com.mohtdon.utilities.PRAYERS_BASE_URL
import com.mohtdon.utilities.hadithBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS).build()

    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Named("provideRetrofitForReciters")
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RECITERS_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    @Singleton
    fun provideRecitersService(@Named("provideRetrofitForReciters") retrofit: Retrofit): RecitersService {
        return retrofit.create(RecitersService::class.java)
    }

    @Named("provideRetrofitForRadio")
    @Provides
    @Singleton
    fun provideRetrofitForRadio(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RADIO_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRadioService(@Named("provideRetrofitForRadio") retrofit: Retrofit): RadioService {
        return retrofit.create(RadioService::class.java)
    }

    @Named("secondRetrofit")
    @Provides
    @Singleton
    fun provideSecondRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(PRAYERS_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideSecondApiService(@Named("secondRetrofit") retrofit: Retrofit): QuranApiService {
        return retrofit.create(QuranApiService::class.java)
    }

    @Named("provideRetrofitForPrayerTimes")
    @Provides
    @Singleton
    fun providePrayerTimesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(PRAYER_TIMES_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providePrayerTimesService(@Named("provideRetrofitForPrayerTimes") retrofit: Retrofit): PrayerTimesService {
        return retrofit.create(PrayerTimesService::class.java)
    }

    @Named("provideRetrofitForHadith")
    @Provides
    @Singleton
    fun provideHadithRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(hadithBaseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideHadithService(@Named("provideRetrofitForHadith") retrofit: Retrofit): HadithService {
        return retrofit.create(HadithService::class.java)
    }
}

