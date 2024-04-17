package com.mohtdon.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.mohtdon.domain.usecases.GetAyaByIdUseCase
import com.mohtdon.domain.usecases.GetDayPrayerTimesUseCase
import com.mohtdon.mohtdon.R
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.home.uistate.HomeUiState
import com.mohtdon.utilities.LATITUDE
import com.mohtdon.utilities.LONGITUDE
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



    private fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }

    private fun getCurrentTime(): LocalTime {
        return LocalTime.now()
    }

    private fun getPrayerTimes(date: String) {
        val latitude = sharedPreferences.getString(LATITUDE, "0.0")?.toDouble()
        val longitude = sharedPreferences.getString(LONGITUDE, "0.0")?.toDouble()

        viewModelScope.launch {
            try {
                val result = getDayPrayerTimesUseCase(date, latitude!!, longitude!!)
                _homeUiState.update {
                    it.copy(dayPrayerTimes = result)
                }
                getTheNextSalahNameAndTime()
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(error = true)
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
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}