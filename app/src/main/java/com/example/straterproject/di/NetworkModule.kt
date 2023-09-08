package com.example.straterproject.di


import com.example.data.dataSource.remote.endPoint.QuranApiService
import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.data.dataSource.remote.service.RadioService
import com.example.data.dataSource.remote.service.RecitersService
import com.example.straterproject.BuildConfig
import com.example.straterproject.utilities.RADIO_BASE_URL
import com.example.straterproject.utilities.RECITERS_BASE_URL
import com.example.straterproject.BuildConfig.PRAYER_TIMES_BASE_URL
import com.example.straterproject.utilities.baseUrl
import com.example.straterproject.utilities.baseUrl1
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

    @Named("provideRetrofitForReciters")

    @Provides
    @Singleton
    fun provideGsonConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(RECITERS_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    @Singleton
    fun provideRecitersService(@Named("provideRetrofitForReciters")retrofit: Retrofit): RecitersService {
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
    fun provideRadioService(@Named("provideRetrofitForRadio")retrofit: Retrofit): RadioService {
        return retrofit.create(RadioService::class.java)
    }




    @Named("secondRetrofit")
    @Singleton
    fun provideSecondRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl1)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideSecondRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl1).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSecondApiService(@Named("secondRetrofit") retrofit: Retrofit): QuranApiService {
        return retrofit.create(QuranApiService::class.java)
    }

    @Named("provideRetrofitForprayerTimes")
    @Provides
    @Singleton
    fun providePrayerTimesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(PRAYER_TIMES_BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providePrayerTimesService(@Named("provideRetrofitForprayerTimes")retrofit: Retrofit): PrayerTimesService {
        return retrofit.create(PrayerTimesService::class.java)
    }


}

