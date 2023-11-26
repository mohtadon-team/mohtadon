package com.example.straterproject.ui.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetAyaByIdUseCase
import com.example.domain.usecases.GetDayPrayerTimesUseCase
import com.example.straterproject.R
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.home.HomeUiState
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import java.util.Random
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDayPrayerTimesUseCase: GetDayPrayerTimesUseCase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val appContext: Context,
    private val getAyaByIdUseCase: GetAyaByIdUseCase
) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()


    init {
        _homeUiState.update {
            it.copy(isLoading = true)
        }
        val currentDate = getCurrentDate().toString()
        getPrayerTimes(currentDate)

        val currentDateInArabic = LocalDate.now(
        ).format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.FULL   // or LONG or MEDIUM or SHORT
            ).withLocale(
                Locale(
                    "ar", "eg"
                )   // English language, with cultural norms of United States.
            )
        ).toString()
        _homeUiState.update {
            it.copy(currentDate = currentDateInArabic)
        }

        getRandomDuaaByRandomNumber()
        getRandomAyaByRandomNumber()
        Log.i("ahmed", "test")

//        try {
//            val inputStream: InputStream = appContext.getAssets().open("quranPages.json")
//            val size = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            val jsonArray = JSONArray(String(buffer))
//            val jsonObject:JSONObject = jsonArray.getJSONObject(5)
//            jsonObject.put("surah_name" , "ahmed")
//            val inputStream: InputStream = appContext.assets.open("quranPages.json")
//            val outputFile = File(appContext.filesDir, "test")
//            val outputStream = outputFile.outputStream()
//            inputStream.use { input ->
//                outputStream.use { output ->
//                    input.copyTo(output)
//                }
//            }
//            val file = File(appContext.filesDir, "test")
//            val jsonString = file.readText()
////            val jsonArray = JsonArray(jsonString)
//            val jsonArray = JSONArray(jsonString)
////            jsonArray.getJSONObject(0).put("surah_name","ahmed")
//            Log.i("ahmed", jsonArray.length().toString())
//
//            for (i in 0..25){
//                jsonArray.getJSONObject(i).put("surah_id" , 8)
//                jsonArray.getJSONObject(i).put("surah_name" , "الأنفالquran ")
//                jsonArray.getJSONObject(i).put("page" , i + 151 )
//            }
//            Log.i("ahmed", jsonArray.toString())
//            // Modify the JSON object
////            jsonObject.put(key, value)
////
////            // Write the modified JSON back to the file
////            file.writeText(jsonObject.toString())
//        }catch (e:Throwable){
//            Log.i("ahmed", "error")
//
//        }

//        val pageNumber = 20 // Change this to the desired page number
//
//        val surah = Constants.getPageSurah(pageNumber)
//        val surah = Constants.getPageSurah(pageNumber)
//        var pageNumber = 30
//        var surahName = when(pageNumber){
//            1 -> "الفاتحة"
//            in 2..49 -> "البقرة"
//
//            in 50..76 -> "آل عمران"
//
//            in 77..106 -> "النساء"
//
//            in 106..127 -> "المائدة"
//
//            in 128..150 -> "الأنعام"
//
//            in 151..176 -> "الأعراف"
//
//
//            in   177..186 -> "الأنفال"
//
//
//            in  187..207 -> "التوبة"
//
//            in 208..221 -> "يونس"
//
//            in 221..235 -> "هود"
//
//            in    235..248 -> "يوسف"
//
//            in   249..255 -> "الرعد"
//
//            in  255..261 -> "ابراهيم"
//
//            in 262..267 -> "الحجر"
//
//            in 267..281 -> "النحل"
//
//            in 282..293 -> "الإسراء"
//
//            in  293..304 -> "الكهف"
//
//            in 305.. 312 -> "مريم"
//
//            in  312..321 -> "طه "
//
//            in  322..331 -> "الأنبياء"
//
//            in 332..341 -> "الحج"
//
//            in  342..349 -> "المؤمنون"
//
//            in 350..359 -> "النور"
//
//            in  359..366 -> "الفرقان"
//
//            in  367..376 -> "الشعراء"
//
//            in 377..385 -> "النمل"
//
//            in 385..396-> "القصص"
//
//            in 396..404 -> "العنكبوت"
//
//            in  404..410 -> "الروم"
//
//            in  404..410 -> "الروم"
//
//            in   411..414-> "لقمان"
//
//            in   415..417 -> "السجدة"
//
//            in   418..427 -> "الأحزاب"
//
//            in    428..434 -> "سبإ"
//
//            in  434..440 -> "فاطر"
//
//            in  440..445-> "يس"
//
//            in  446..452-> "الصافات"
//
//            in   453..458 -> "ص"
//
//            in  458..467 -> "الزمر"
//
//            in   467..476 -> "غافر"
//
//            in  477..482-> "فصلت"
//
//            in 483..489 -> "الشورى"
//
//            in  489..495 -> "الزخرف"
//
//
//
//        }
    }

    fun refreshTheDifferenceBetweenCurrentAndNextSalahTime() {
        getTheNextSalahNameAndTime()
    }

    private fun calculateTheDifferenceBetweenCurrentAndNextSalahTime(nextSalah: LocalTime) {
        val currentTime = getCurrentTime()
        val differenceDuration = Duration.between(currentTime, nextSalah)
        _homeUiState.update {
            it.copy(
                currentTimeAndNextSalahTimeDifference = LocalTime.of(
                    differenceDuration.toHours().toInt(),
                    (differenceDuration.toMinutes() % 60).toInt()
                ).toString(),
                thePercentageBetweenCurrentTimeAndNextSalahTime = (differenceDuration.toMillis() % 100).toString()
            )
        }
    }

    private fun getTheNextSalahNameAndTime() {

        val todayPrayerTimes = _homeUiState.value.dayPrayerTimes
        val currentTime = getCurrentTime()

        if (currentTime.isBefore(todayPrayerTimes?.fajr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = (appContext.getString(R.string.fajr)),
                    nextSalahTime = todayPrayerTimes?.fajr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_isha)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.fajr)
        } else if (currentTime.isBefore(todayPrayerTimes?.dhuhr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = (appContext.getString(R.string.dhuhr)),
                    nextSalahTime = todayPrayerTimes?.dhuhr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_fajr)
                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.dhuhr)
        } else if (currentTime.isBefore(todayPrayerTimes?.asr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = (appContext.getString(R.string.asr)),
                    nextSalahTime = todayPrayerTimes?.asr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_dhuhr)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.asr)
        } else if (currentTime.isBefore(todayPrayerTimes?.maghrib)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = (appContext.getString(R.string.maghrib)),
                    nextSalahTime = todayPrayerTimes?.maghrib?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_asr)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.maghrib)

        } else if (currentTime.isBefore(todayPrayerTimes?.isha)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = (appContext.getString(R.string.isha)),
                    nextSalahTime = todayPrayerTimes?.isha?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_maghrib)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.isha)

        } else {

            if (_homeUiState.value.nextSalahTime.isEmpty() || (!_homeUiState.value.nextSalahName.equals(
                    (appContext.getString(R.string.fajr))
                ) && LocalTime.parse(_homeUiState.value.nextSalahTime)
                    .isBefore(LocalTime.parse("24:00")))
            ) {
                getNextDayPrayerTimes()
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        nextSalahName = (appContext.getString(R.string.fajr)),
                        nextSalahTime = todayPrayerTimes?.fajr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                            .toString(),
                        doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_isha)

                    )
                }
                calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.fajr)
            }
        }
    }

    private fun getNextDayPrayerTimes() {
        val nextDayDate = getCurrentDate().plusDays(1).toString()
        getPrayerTimes(nextDayDate)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    private fun getCurrentTime(): LocalTime {
        return LocalTime.now()
    }

    private fun getPrayerTimes(
        date: String
    ) {

        val latitude = sharedPreferences.getString(LATITUDE, "0.0")?.toDouble()
        val longitude = sharedPreferences.getString(LONGITUDE, "0.0")?.toDouble()


        viewModelScope.launch {
            try {
                val result = getDayPrayerTimesUseCase(date, latitude!!, longitude!!)
                _homeUiState.update {
                    it.copy(
                        dayPrayerTimes = result
                    )
                }
                getTheNextSalahNameAndTime()
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(
                        error = true
                    )
                }
            }
        }
    }


    private fun getRandomDuaaByRandomNumber() {
        val randomDuaaNumber = Random().nextInt(50 - 0)
        var duaa = ""
        try {
            val inputStream: InputStream = appContext.assets.open("duaa/duaa.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val duaaObject = JSONObject(String(buffer))
            val duaaArray = duaaObject.getJSONArray("data")
            duaa = duaaArray.getJSONObject(randomDuaaNumber).getString("text")
            _homeUiState.update {
                it.copy(
                    randomDuaa = duaa
//                    randomDuaa = "sjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjk"

                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getRandomAyaByRandomNumber() {
        val randomAyahNumber = Random().nextInt(50 - 0)
        var ayah = ""
        try {
            val inputStream: InputStream = appContext.getAssets().open("quran/ayat.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val ayaObject = JSONObject(String(buffer))
            val ayaArray = ayaObject.getJSONArray("data")
            ayah = ayaArray.getJSONObject(randomAyahNumber).getString("text")
            _homeUiState.update {
                it.copy(
                    randomAya = ayah
//                    randomAya = "sjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjksjkdfghfjksdhjkdfshjkghdjkfhgjkldfshgjklfhgjklhjklfsdhgjkfgjkfhjkdshagjklsahjklfghjk"

                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}