package com.example.straterproject.di


import com.example.data.dataSource.remote.ApiService
import com.example.data.dataSource.remote.endPoint.QuranApiService

import com.example.data.dataSource.remote.service.PrayerTimesService
import com.example.straterproject.BuildConfig
import com.example.straterproject.utilities.baseUrl
import com.example.straterproject.utilities.baseUrl1
import com.google.gson.Gson
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Named("secondRetrofit")
    @Provides
    @Singleton
    fun provideSecondRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl1)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideSeconApiService(@Named("secondRetrofit") retrofit: Retrofit): QuranApiService {
        return retrofit.create(QuranApiService::class.java)
    }

        @Singleton
        @Provides
        @Named("prayer_times")
        fun providePrayerTimesService(
            @Named("prayer_times") retrofit: Retrofit
        ): PrayerTimesService {
            return retrofit.create(PrayerTimesService::class.java)
        }

        @Singleton
        @Provides
        @Named("prayer_times")
        fun providePrayerTimesRetrofit(
            @Named("prayer_times") client: OkHttpClient,
            @Named("prayer_times") gsonConverterFactory: GsonConverterFactory
        ): Retrofit {
            return Retrofit.Builder().baseUrl(BuildConfig.PRAYER_TIMES_BASE_URL).client(client)
                .addConverterFactory(gsonConverterFactory).build()

        }

            @Provides
            @Named("prayer_times")
            fun providePrayerTimesOkHttpClient(): OkHttpClient {
                return OkHttpClient.Builder().build()
            }

            @Singleton
            @Provides
            @Named("prayer_times")
            fun providePrayerTimesGsonConverterFactory(): GsonConverterFactory {
                return GsonConverterFactory.create()
            }

            @Singleton
            fun provideSecondApiService(@Named("secondRetrofit") retrofit: Retrofit): QuranApiService {
                return retrofit.create(QuranApiService::class.java)}

                @Provides
                @Named("prayer_times")
                fun providePrayerTimesGson(): Gson {
                    return Gson()
                }

            }

