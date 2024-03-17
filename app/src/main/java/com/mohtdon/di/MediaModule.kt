package com.mohtdon.di

import android.content.Context

import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MediaModule {

    @Provides
    @Singleton
    fun provideAudioAttributes(): AudioAttributes =
        AudioAttributes.Builder().setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).setUsage(C.USAGE_MEDIA)
            .build()

    @Provides
    @Singleton
    fun providePlayer(
        @ApplicationContext context: Context, audioAttributes: AudioAttributes
    ): ExoPlayer = ExoPlayer.Builder(context).setAudioAttributes(audioAttributes, true)
        .setHandleAudioBecomingNoisy(true).build()


    @Provides
    @Singleton
    fun provideMediaSession(
        @ApplicationContext context: Context, player: ExoPlayer
    ): MediaSession = MediaSession.Builder(context, player).build()



}