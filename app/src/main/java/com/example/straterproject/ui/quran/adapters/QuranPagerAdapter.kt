package com.example.straterproject.ui.quran.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.straterproject.ui.quran.fragments.QuranPageFragment


class QuranPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
     val TOTAL_PAGES_NUM = 604

    override fun createFragment(position: Int): Fragment {
        return  QuranPageFragment(TOTAL_PAGES_NUM - position)
    }

    override fun getItemCount(): Int {
        return TOTAL_PAGES_NUM
    }
}