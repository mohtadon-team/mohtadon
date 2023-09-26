package com.example.straterproject.ui.quran.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.straterproject.ui.quran.fragments.QuranFragment
import com.example.straterproject.ui.quran.fragments.SavedQuranPagesFragment

class QuranTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuranFragment()
            1 -> SavedQuranPagesFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}