package com.example.straterproject.ui.fragments.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.await
import com.example.domain.models.todayPrayerTimes.TodayPrayerTimes
import com.example.domain.usecases.GetTodayPrayerTimesUseCase
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import com.example.straterproject.utilities.SALAH_NAME
import kotlinx.coroutines.runBlocking
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RefreshPrayerTimesForWorker(
    private val context: Context, private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var getTodayPrayerTimesUseCase: GetTodayPrayerTimesUseCase

    @RequiresApi(Build.VERSION_CODES.O)
    override  fun doWork(): Result {
        val currentDate = getCurrentDate().toString()
        var todayPrayerTimes: ArrayList<LocalTime> = ArrayList<LocalTime>()
        var prayers = listOf<String>("fajr", "dhuhr", "asr", "maghrib", "isha")

        Log.i("ahmed", "worker 1 ")

        runBlocking {
            todayPrayerTimes = getPrayerTimes(currentDate)
            for (i in 0..4) {
                var salahName = prayers.get(i)
                var data: Data = Data.Builder().putString(SALAH_NAME, salahName).build()
                var salahTime = todayPrayerTimes.get(i)
                var initialDelay = calculateDifferenceBetweenNowAndSalahTime(salahTime)
                var prayerTag = currentDate + salahName
//                makeWorkerForEachSalah(prayerTag, initialDelay, data)

            }
        }

        val registerPrayerRequest: OneTimeWorkRequest =
            OneTimeWorkRequest.Builder(AzanWorker::class.java)
                .addTag(LocalDate.now().toString() + "ahmed")  // this to prevent the repeat if more prayer have the same time
                .setInitialDelay(2, TimeUnit.MILLISECONDS)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
            LocalDate.now().toString() + "ahmed",
            ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
            registerPrayerRequest
        )
//        val registerPrayerRequest: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(AzanWorker::class.java).addTag(
//                LocalDate.now().toString()
//            )  // this to prevent the repeat if more prayer have the same time
////                .setConstraints(constraints)
////                .setInitialDelay(10 , TimeUnit.SECONDS)
//                .build()
//
//
//        var innerWorker = WorkManager.getInstance(context).enqueueUniqueWork(
//            "test2", ExistingWorkPolicy.KEEP,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            registerPrayerRequest
//        )
//
//        innerWorker.await()
//        val registerPrayerRequest2: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(AzanWorker::class.java)
////                .addTag(
////                "test1"
////            )  // this to prevent the repeat if more prayer have the same time
////                .setConstraints(constraints)
//                .build()
//
//
//        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
//            "test",
//            ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            registerPrayerRequest2
//        )
//        val registerPrayerRequest: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(AzanWorker::class.java)
//
//                .addTag(
//                LocalDate.now().toString()
//            )
//                // this to prevent the repeat if more prayer have the same time
////                .setConstraints(constraints)
//                .build()
//
//
//        WorkManager.getInstance(context.applicationContext).enqueueUniqueWork(
//            LocalDate.now().toString(),
//            ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            registerPrayerRequest
//        )
//        val registerPrayerRequest: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(AzanWorker::class.java).addTag(
//                LocalDate.now().toString()
//            )  // this to prevent the repeat if more prayer have the same time
////                .setConstraints(constraints)
//                .build()
//
//
//        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
//            LocalDate.now().toString(),
//            ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            registerPrayerRequest
//        )
//        makeWorkerForEachSalah("hello", 10 , null )

        return Result.success()
    }

//    private fun makeWorkerForEachSalah(prayerTag: String, initialDelay: Long, data: Data) {
//        val refreshPrayerTimesForRequest: OneTimeWorkRequest =
//            OneTimeWorkRequest.Builder(AzanWorker::class.java)
//                .addTag(prayerTag)  // this to prevent the repeat if more prayer have the same time
//                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS).setInputData(data)
//                .setInitialDelay(initialDelay, TimeUnit.SECONDS).build()
//
//        Log.i("ahmed", "worker 1 ")
//        WorkManager.getInstance(applicationContext).enqueueUniqueWork(
//            prayerTag, ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
//            refreshPrayerTimesForRequest
//        )
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateDifferenceBetweenNowAndSalahTime(salahTime: LocalTime?): Long {
        val currentTime = getCurrentTime()
        val differenceDuration = Duration.between(currentTime, salahTime)
        return differenceDuration.toMillis()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime(): LocalTime {
        return LocalTime.now()
    }

    private suspend fun getPrayerTimes(
        date: String
    ): ArrayList<LocalTime> {

        val latitude = sharedPreferences.getString(LATITUDE, "0.0")?.toDouble()
        val longitude = sharedPreferences.getString(LONGITUDE, "0.0")?.toDouble()

        var todayPrayerTimes: TodayPrayerTimes? = null
        var prayersArray: ArrayList<LocalTime> = ArrayList<LocalTime>()
        try {
            todayPrayerTimes = getTodayPrayerTimesUseCase(date, latitude!!, longitude!!)
            prayersArray.add(todayPrayerTimes.Fajr)
            prayersArray.add(todayPrayerTimes.Dhuhr)
            prayersArray.add(todayPrayerTimes.Asr)
            prayersArray.add(todayPrayerTimes.Maghrib)
            prayersArray.add(todayPrayerTimes.Isha)
        } catch (e: java.lang.Exception) {
            Log.i("ahmed", e.message.toString())
        }
        return prayersArray
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }


}