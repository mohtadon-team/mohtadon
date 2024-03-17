package com.mohtdon.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
    class MusicPlaybackModule {
        @Provides
        fun provideContext(application: Application): Context {
            return application.applicationContext
    }
}