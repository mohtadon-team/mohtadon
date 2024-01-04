package com.example.mohtdon.ui.hijriCalender

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mohtdon.R
import com.example.mohtdon.databinding.FragmentHijriCalenderBinding
import com.example.mohtdon.ui.base.BaseFragment
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