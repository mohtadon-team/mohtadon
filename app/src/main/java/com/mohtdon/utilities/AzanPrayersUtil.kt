package com.mohtdon.utilities

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.mohtdon.utilities.notification.RegisterPrayerTimesWorker
import java.util.concurrent.TimeUnit

class AzanPrayersUtil {
    fun registerPrayers(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelAllWork()

        val registerRequest = PeriodicWorkRequest.Builder(
            RegisterPrayerTimesWorker::class.java, 30, TimeUnit.DAYS
        ).addTag("REGISTER_PRAYERS").build()

        WorkManager.getInstance(context.applicationContext)
            .enqueueUniquePeriodicWork(
                "REGISTER_PRAYERS", ExistingPeriodicWorkPolicy.KEEP, registerRequest
            )
    }
}