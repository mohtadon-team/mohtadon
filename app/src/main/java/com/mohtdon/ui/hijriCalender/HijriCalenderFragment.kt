package com.mohtdon.ui.hijriCalender

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentHijriCalenderBinding
import com.mohtdon.ui.base.BaseFragment
//import com.github.eltohamy.materialhijricalendarview.CalendarDay


class HijriCalenderFragment : BaseFragment<FragmentHijriCalenderBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_hijri_calender
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val currentDate = CalendarDay.today()
//        binding.calendarView.setSelectedDate(currentDate)

    }

}