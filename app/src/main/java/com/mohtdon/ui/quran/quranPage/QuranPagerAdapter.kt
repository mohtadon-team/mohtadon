package com.mohtdon.ui.quran.quranPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohtdon.utilities.TOTAL_QURAN_PAGES_NUMBER


class QuranPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {


    override fun createFragment(position: Int): Fragment {
        return QuranPageFragment(TOTAL_QURAN_PAGES_NUMBER - position)
    }


    override fun getItemCount(): Int {
        return TOTAL_QURAN_PAGES_NUMBER
    }
}