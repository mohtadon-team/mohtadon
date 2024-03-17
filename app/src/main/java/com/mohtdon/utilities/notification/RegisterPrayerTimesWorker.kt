package com.mohtdon.utilities.notification

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mohtdon.data.dataSource.remote.service.PrayerTimesService
import com.mohtdon.data.dataSource.repository.PrayerTimes.PrayerTimesRepositoryImp
import com.mohtdon.data.utils.Constant.Companion.NOTIFICATION_CONTENT_KEY
import com.mohtdon.data.utils.Constant.Companion.NOTIFICATION_TITLE_KEY
import com.mohtdon.domain.models.prayerTimes.PrayersTiming
import com.mohtdon.domain.models.prayerTimes.DayPrayerTimes
import com.mohtdon.domain.usecases.GetMonthPrayerTimesUseCase
import com.mohtdon.mohtdon.BuildConfig
import com.mohtdon.utilities.SHARED_PREFERENCES_NAME
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

//@HiltWorker
class RegisterPrayerTimesWorker(
    var context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {


    override fun doWork(): Result {


        return try {
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH] + 1


            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.PRAYER_TIMES_BASE_URL).client(
                OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS).build()
            ).addConverterFactory(GsonConverterFactory.create()).build()

            val prayerTimesService = retrofit.create(PrayerTimesService::class.java)


            val sharedPreferences = applicationContext.getSharedPreferences(
                SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE
            )

            val latitude = sharedPreferences.getString("latitude", "0.0")?.toDouble()
            val longitude = sharedPreferences.getString("longitude", "0.0")?.toDouble()
            Log.i("mohamed", latitude.toString() + "   " + longitude.toString())

            val getMonthPrayerTimesUseCase =
                GetMonthPrayerTimesUseCase(PrayerTimesRepositoryImp(prayerTimesService))
            runBlocking {
                val response = getMonthPrayerTimesUseCase(month, year, latitude!!, longitude!!)

                val data = response.data   // return all day in one month
                for (day in 1..data.size) {

                    val prayerTimings = data[day]   // هيجيب كل list من الصلاوات الي في اليوم الواحد
                    val prayers =
                        convertFromTimings(prayerTimings)  // هنا هيجيب الاوقات الي محتاجها فقط
                    prayers.forEach { prayerTiming ->
                        val prayerTag = "$year/$month/$day ${prayerTiming.prayersName}"
                        val delay: Long = calculatePrayerDelay(
                            year, month, day, prayerTiming
                        ) // الوقت المنتظر لهذه الصلاه

                        if (delay > 0) {
                            val input = Data.Builder().putString(
                                NOTIFICATION_TITLE_KEY, "صلاة " + prayerTiming.prayersName
                            ).putString(NOTIFICATION_CONTENT_KEY, "حان الآن موعد الصلاة").build()

                            val registerPrayerRequest: OneTimeWorkRequest =
                                OneTimeWorkRequest.Builder(AzanNotificationWorker::class.java)
                                    .addTag(prayerTag)  // this to prevent the repeat if more prayer have the same time
                                    .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                                    .setInputData(input).build()

                            WorkManager.getInstance(applicationContext).enqueueUniqueWork(
                                prayerTag,
                                ExistingWorkPolicy.REPLACE,  // هنا لو حصل تكرار هيشيل القديم ويحط الجديد
                                registerPrayerRequest
                            )
                        }
                    }

                }
            }

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }


    // هذه function علشان تحسب الوقت المنظر لكل صلاه في اليوم
    private fun calculatePrayerDelay(
        year: Int, month: Int, day: Int, prayerTiming: PrayersTiming
    ): Long {
        val pattern = "yyyy/MM/dd HH:mm"
        val decimalFormat = DecimalFormat("00")
        val time: String = prayerTiming.prayersTime.split(" ").get(0)
        val prayerDate = "$year/${decimalFormat.format(month)}/${decimalFormat.format(day)} $time"
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return try {
            val date: Date = format.parse(prayerDate)!!    // convert string to date
            val currentTime = System.currentTimeMillis()
            Log.d("TAG", "calculatePrayerDelay: $date")
            Log.d(
                "TAG", "calculatePrayerDelay: diff = " + (date.time - currentTime) + " " + date.time
            )

            date.time - currentTime

        } catch (e: ParseException) {
            e.printStackTrace()
            -1
        }
    }


    private fun convertFromTimings(dayPrayerTimes: DayPrayerTimes): ArrayList<PrayersTiming> {
        val result: ArrayList<PrayersTiming> = ArrayList()
        result.add(PrayersTiming("Fajr", dayPrayerTimes.fajr.toString(), "AM"))
        result.add(PrayersTiming("Dhuhr", dayPrayerTimes.dhuhr.toString(), "AM"))
        result.add(PrayersTiming("Asr", dayPrayerTimes.asr.toString(), "PM"))
        result.add(PrayersTiming("Maghrib", dayPrayerTimes.maghrib.toString(), "PM"))
        result.add(PrayersTiming("Isha", dayPrayerTimes.isha.toString(), "PM"))
        return result
    }

}