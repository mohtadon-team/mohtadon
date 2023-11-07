package com.example.straterproject

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.domain.usecases.GetMonthPrayerTimesUseCase
import com.example.straterproject.utilities.notification.RegisterPrayerTimesWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application()
// , Configuration.Provider {
//
//    @Inject
//    lateinit var workerFactory: CustomWorkerFactory
//
//
//    override fun getWorkManagerConfiguration() =
//        Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG).setWorkerFactory(workerFactory)
//            .build()
//
//}
//
//
//class CustomWorkerFactory @Inject constructor(
////    val sharedPreferences: SharedPreferences,
////    val getMonthPrayerTimesUseCase: GetMonthPrayerTimesUseCase
//) : WorkerFactory() {
//    override fun createWorker(
//        appContext: Context, workerClassName: String, workerParameters: WorkerParameters
//    ): ListenableWorker? = RegisterPrayerTimesWorker(
//        appContext, workerParameters
////        , sharedPreferences, getMonthPrayerTimesUseCase
//    )
//
//}