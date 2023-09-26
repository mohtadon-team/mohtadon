package com.example.straterproject.ui.viewModels

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetTodayPrayerTimesUseCase
import com.example.straterproject.R
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.fragments.home.HomeUIEvent
import com.example.straterproject.ui.fragments.home.HomeUiState
import com.example.straterproject.utilities.Event
import com.example.straterproject.utilities.LATITUDE
import com.example.straterproject.utilities.LONGITUDE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodayPrayerTimesUseCase: GetTodayPrayerTimesUseCase,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val _homeUiEvent = MutableStateFlow<Event<HomeUIEvent?>>(Event(null))
    val homeUIEvent = _homeUiEvent.asStateFlow()


    val test = "ahmed"
    init {
        _homeUiState.update {
            it.copy(isLoading = true)
        }


        val currentDate = getCurrentDate().toString()

        getPrayerTimes(currentDate)


    }

    fun refreshTheDifferenceBetweenCurrentAndNextSalahTime() {
        getTheNextSalahNameAndTime()
    }

    private fun calculateTheDifferenceBetweenCurrentAndNextSalahTime(nextSalah: LocalTime) {
        val currentTime = getCurrentTime()
        val differenceDuration = Duration.between(currentTime, nextSalah)

//        Log.i("ahmed", differenceDuration.toHours().toString())
        _homeUiState.update {
            it.copy(
                currentTimeAndNextSalahTimeDifference = LocalTime.of(
                    differenceDuration.toHours().toInt(),
                    (differenceDuration.toMinutes() % 60).toInt()
                ).toString() ,
                thePercentageBetweenCurrentTimeAndNextSalahTime = (differenceDuration.toMillis() %100 ).toString()
            )
        }
    }

    private fun getTheNextSalahNameAndTime() {

        val todayPrayerTimes = _homeUiState.value.todayPrayerTimes
        val currentTime = getCurrentTime()

        if (currentTime.isBefore(todayPrayerTimes?.Fajr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = "Fajr",
                    nextSalahTime = todayPrayerTimes?.Fajr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_isha)


                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Fajr)
        } else if (currentTime.isBefore(todayPrayerTimes?.Dhuhr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = "Dhuhr",
                    nextSalahTime = todayPrayerTimes?.Dhuhr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_fajr)
                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Dhuhr)
        } else if (currentTime.isBefore(todayPrayerTimes?.Asr)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = "Asr",
                    nextSalahTime = todayPrayerTimes?.Asr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_dhuhr)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Asr)

        } else if (currentTime.isBefore(todayPrayerTimes?.Maghrib)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = "Maghrib",
                    nextSalahTime = todayPrayerTimes?.Maghrib?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_asr)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Maghrib)

        } else if (currentTime.isBefore(todayPrayerTimes?.Isha)) {
            _homeUiState.update {
                it.copy(
                    isLoading = false,
                    nextSalahName = "Isha",
                    nextSalahTime = todayPrayerTimes?.Isha?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                        .toString(),
                    doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_maghrib)

                )
            }
            calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Isha)

        } else {

            if (_homeUiState.value.nextSalahTime.isNullOrEmpty() || (!_homeUiState.value.nextSalahName.equals(
                    "Fajr"
                ) && LocalTime.parse(_homeUiState.value.nextSalahTime)
                    .isBefore(LocalTime.parse("24:00")))
            ) {
                getNextDayPrayerTimes()
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        nextSalahName = "Fajr",
                        nextSalahTime = todayPrayerTimes?.Fajr?.format(DateTimeFormatter.ofPattern("hh:mm a"))
                            .toString(),
                        doYouPrayTheLastSalah = appContext.getString(R.string.do_you_pray_isha)

                    )
                }
                Log.i("ahmed", "getTheNextSalahNameAndTime: ")
                calculateTheDifferenceBetweenCurrentAndNextSalahTime(todayPrayerTimes!!.Fajr)
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
                var result = getTodayPrayerTimesUseCase(date, latitude!!, longitude!!)
                _homeUiState.update {
                    it.copy(
                        todayPrayerTimes = result
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
}