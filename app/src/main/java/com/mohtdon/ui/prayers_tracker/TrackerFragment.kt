package com.mohtdon.ui.prayers_tracker

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView.OnDateChangeListener
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentTrackerBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.prayers_tracker.adapter.CalenderSalahRvAdapter
import com.mohtdon.ui.prayers_tracker.adapter.OnWeeklyCalenderListener
import com.mohtdon.ui.prayers_tracker.adapter.WeekCalenderRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar


@AndroidEntryPoint
class TrackerFragment : BaseFragment<FragmentTrackerBinding>(), OnWeeklyCalenderListener,
    CalenderSalahRvAdapter.OnPrayersCheckedItemListener {
    override val layoutFragmentId: Int = R.layout.fragment_tracker
    override val viewModel: TrackerViewModel by viewModels()

    lateinit var weekCalenderRvAdapter: WeekCalenderRvAdapter
    lateinit var calenderSalahRvAdapter: CalenderSalahRvAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setWeekView()
        setMonthView()
        setChangeMonthButtonsListener()
        setMonthViewClickListener()
        setSwitchCalenderViewClickListener()
        initalizeCalender()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initalizeCalender() {
        TrackerViewModel.selectedDate = LocalDate.now()
        val currentDate = Calendar.getInstance().timeInMillis
        binding.monthlyCalendarView.date = currentDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setChangeMonthButtonsListener() {
        binding.nextWeekButton.setOnClickListener {
            nextWeekAction()
        }
        binding.previousWeekButton.setOnClickListener {
            previousWeekAction()
        }
    }

    private fun setSwitchCalenderViewClickListener() {
        binding.switchCalenderViewImage.setOnClickListener {
            if (binding.monthGroup.visibility == View.GONE) {
                binding.monthGroup.visibility = View.VISIBLE
                binding.weeklyGroup.visibility = View.GONE
            } else {
                binding.monthGroup.visibility = View.GONE
                binding.weeklyGroup.visibility = View.VISIBLE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthViewClickListener() {
        binding.monthlyCalendarView.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->

            val selectedDate = LocalDate.of(year, month +1 , dayOfMonth)
            Log.i("ahmed", "$dayOfMonth $month $year")
            TrackerViewModel.selectedDate = selectedDate
            setMonthView()

            if (selectedDate!!.isEqual(LocalDate.now())) {
                viewModel.getdayPrayerTimes(LocalDate.now(), true)
            } else {
                viewModel.getdayPrayerTimes(selectedDate, false)
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {


        lifecycleScope.launch {
            viewModel.trackerUiState.collect {
                calenderSalahRvAdapter = CalenderSalahRvAdapter(it.dayPrayers, this@TrackerFragment)
                binding.monthlyCalenderRv.adapter = calenderSalahRvAdapter
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekView() {
        binding.currentMonth.text = viewModel.monthYearFromDate(TrackerViewModel.selectedDate)
        val days: ArrayList<LocalDate> = viewModel.daysInWeekArray(TrackerViewModel.selectedDate)
        weekCalenderRvAdapter = WeekCalenderRvAdapter(days, this)


        lifecycleScope.launch {
            viewModel.trackerUiState.collect {
                Log.d("ahmed", "Collect block triggered. todayPrayersProgress: ${it.todayPrayersProgress}")
                calenderSalahRvAdapter = CalenderSalahRvAdapter(it.dayPrayers, this@TrackerFragment)
                binding.weeklyCalenderSalahRv.adapter = calenderSalahRvAdapter

                binding.dailyPrayersProgress.progress = it.todayPrayersProgress
                ("${(it.todayPrayersProgress * 20)}%").also {
                    binding.dailyPrayersProgressPercentage.text = it
                }
            }
        }
        binding.weeklyCalender.adapter = weekCalenderRvAdapter
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun previousWeekAction() {
        TrackerViewModel.selectedDate = TrackerViewModel.selectedDate!!.minusWeeks(1)
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextWeekAction() {
        TrackerViewModel.selectedDate = TrackerViewModel.selectedDate!!.plusWeeks(1)
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate?) {
        TrackerViewModel.selectedDate = date
        setWeekView()
        if (date!!.equals(LocalDate.now())) {
            viewModel.getdayPrayerTimes(LocalDate.now(), true)
        } else {
            viewModel.getdayPrayerTimes(date, false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(salahName: String, value: Boolean) {
        viewModel.setTodayPerformSalah(salahName, value)
    }


}