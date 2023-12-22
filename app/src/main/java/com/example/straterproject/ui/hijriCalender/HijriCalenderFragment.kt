package com.example.straterproject.ui.hijriCalender

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHijriCalenderBinding
import com.example.straterproject.ui.base.BaseFragment
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