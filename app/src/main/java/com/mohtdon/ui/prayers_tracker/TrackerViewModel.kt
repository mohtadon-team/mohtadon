package com.mohtdon.ui.prayers_tracker

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.mohtdon.domain.usecases.GetDayPrayerTimesUseCase
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.prayers_tracker.models.Salah
import com.mohtdon.ui.prayers_tracker.uistate.TrackerUiState
import com.mohtdon.utilities.*
import com.mohtdon.utilities.ASR
import com.mohtdon.utilities.DHUHR
import com.mohtdon.utilities.FAJR
import com.mohtdon.utilities.ISHA
import com.mohtdon.utilities.IS_ASR_PERFORMED
import com.mohtdon.utilities.IS_DHUHR_PERFORMED
import com.mohtdon.utilities.IS_FAJR_PERFORMED
import com.mohtdon.utilities.IS_ISHA_PERFORMED
import com.mohtdon.utilities.IS_MAGHRIB_PERFORMED
import com.mohtdon.utilities.LATITUDE
import com.mohtdon.utilities.LONGITUDE
import com.mohtdon.utilities.MAGHRIB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val getDayPrayerTimesUseCase: GetDayPrayerTimesUseCase,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    lateinit var editor: SharedPreferences.Editor

    private val _trackerUiState = MutableStateFlow(TrackerUiState())
    val trackerUiState = _trackerUiState.asStateFlow()

    init {

        val currentDateInArabic = LocalDate.now(
        ).format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.FULL   // or LONG or MEDIUM or SHORT
            ).withLocale(
                Locale(
                    "ar", "eg"
                )   // English language, with cultural norms of United States.
            )
        ).toString().replaceBefore(" ", " ").trim()
        _trackerUiState.update {
            it.copy(currentDate = currentDateInArabic)
        }


        editor = sharedPreferences.edit()

        getdayPrayerTimes(LocalDate.now(), true)
        getTodayPrayersPerformedProgress()

    }

    fun getdayPrayerTimes(date: LocalDate?, isItToday: Boolean) {
        val latitude = sharedPreferences.getString(LATITUDE, "0.0")?.toDouble()
        val longitude = sharedPreferences.getString(LONGITUDE, "0.0")?.toDouble()
        viewModelScope.launch {
            val dayPrayerTimes = getDayPrayerTimesUseCase(date.toString(), latitude!!, longitude!!)

            _trackerUiState.update {
                it.copy(
                    dayPrayers = listOf(
                        Salah(
                            FAJR,
                            getTodayPerformSalah(FAJR),
                            dayPrayerTimes?.fajr.toString(),
                            isItToday
                        ),
                        Salah(
                            DHUHR,
                            getTodayPerformSalah(DHUHR),
                            dayPrayerTimes?.dhuhr.toString(),
                            isItToday
                        ),
                        Salah(
                            ASR, getTodayPerformSalah(ASR), dayPrayerTimes?.asr.toString(), isItToday
                        ),
                        Salah(
                            MAGHRIB,
                            getTodayPerformSalah(MAGHRIB),
                            dayPrayerTimes?.maghrib.toString(),
                            isItToday
                        ),
                        Salah(
                            ISHA,
                            getTodayPerformSalah(ISHA),
                            dayPrayerTimes?.isha.toString(),
                            isItToday
                        ),
                    )

                )
            }
        }
    }


    fun getTodayPrayersPerformedProgress(): Int {
        var result = 0
        val prayerArray = listOf(FAJR, DHUHR, ASR, MAGHRIB, ISHA)

        prayerArray.forEach {
            Log.d("performed", "getTodayPrayersPerformedProgress: "+getTodayPerformSalah(it))
            if (getTodayPerformSalah(it)) {
                result++
                Log.d("result int", "getTodayPrayersPerformedProgress: $result")
            }
        }
        _trackerUiState.value = _trackerUiState.value.copy(todayPrayersProgress = result)

//        _trackerUiState.update {
//            _trackerUiState.value = it.copy(todayPrayersProgress = result)
//            it.copy(todayPrayersProgress = result)
//        }

        return result
    }

    fun getTodayPerformSalah(salahName: String): Boolean {

        return when (salahName) {
            FAJR -> sharedPreferences.getBoolean(IS_FAJR_PERFORMED, false)
            DHUHR -> sharedPreferences.getBoolean(IS_DHUHR_PERFORMED, false)
            ASR -> sharedPreferences.getBoolean(IS_ASR_PERFORMED, false)
            MAGHRIB -> sharedPreferences.getBoolean(IS_MAGHRIB_PERFORMED, false)
            ISHA -> sharedPreferences.getBoolean(IS_ISHA_PERFORMED, false)
            else -> {
                false
            }
        }

    }

    fun setTodayPerformSalah(salahName: String, value: Boolean) {

        when (salahName) {
            FAJR -> putSalahIsPerformedIntoSharedPreferences(IS_FAJR_PERFORMED, value)
            DHUHR -> putSalahIsPerformedIntoSharedPreferences(IS_DHUHR_PERFORMED, value)
            ASR -> putSalahIsPerformedIntoSharedPreferences(IS_ASR_PERFORMED, value)
            MAGHRIB -> putSalahIsPerformedIntoSharedPreferences(IS_MAGHRIB_PERFORMED, value)
            ISHA -> putSalahIsPerformedIntoSharedPreferences(IS_ISHA_PERFORMED, value)
        }
        getTodayPrayersPerformedProgress()
        getdayPrayerTimes(LocalDate.now() , true)
    }

    private fun putSalahIsPerformedIntoSharedPreferences(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date!!.format(formatter)
    }


    @RequiresApi(Build.VERSION_CODES.O)

    fun daysInWeekArray(selectedDate: LocalDate?): ArrayList<LocalDate> {
        val days = ArrayList<LocalDate>()
        var current = sundayForDate(selectedDate)
        val endDate = current!!.plusWeeks(1)
        while (current!!.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sundayForDate(current: LocalDate?): LocalDate? {
        var current = current
        val oneWeekAgo = current!!.minusWeeks(1)
        while (current!!.isAfter(oneWeekAgo)) {
            if (current!!.dayOfWeek == DayOfWeek.SUNDAY) return current
            current = current.minusDays(1)
        }
        return null
    }

    companion object {
        var selectedDate: LocalDate? = LocalDate.now()
    }


}

