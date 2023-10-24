package com.example.straterproject.ui.fragments.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class AlarmReceiver : BroadcastReceiver() {

    fun registerPrayers(context: Context) {
        WorkManager.getInstance(context.applicationContext).cancelAllWork()

        val registerRequest = PeriodicWorkRequest.Builder(
            RefreshPrayerTimesForWorker::class.java, 30, TimeUnit.DAYS
        ).addTag("REGISTER_PRAYERS").build()

        WorkManager.getInstance(context.applicationContext).enqueueUniquePeriodicWork(
            "REGISTER_PRAYERS", ExistingPeriodicWorkPolicy.KEEP, registerRequest
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        Log.i("ahmed", "alarm reciever")

        registerPrayers(context)
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Require network connectivity
            .build()


//        val registerPrayerRequest: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(RefreshPrayerTimesForWorker::class.java).addTag(
//                LocalDate.now().toString()
//            )  // this to prevent the repeat if more prayer have the same time
////                .setInitialDelay(10 , TimeUnit.SECONDS)
//                .setConstraints(constraints).build()
//
//
//        WorkManager.getInstance(context.applicationContext).enqueueUniqueWork(
//            "test1",
//            ExistingWorkPolicy.KEEP,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            registerPrayerRequest
//        )


    }
}