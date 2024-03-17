package com.mohtdon

import android.app.Application
//import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
//     AndroidThreeTen.init(this)
    }
}
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