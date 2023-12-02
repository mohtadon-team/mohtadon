package com.example.straterproject.ui.quran.quranTaps

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.straterproject.ui.quran.quranList.QuranListFragment
import com.example.straterproject.ui.quran.savedQuranPages.SavedQuranPagesFragment

class QuranTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuranListFragment()
            1 -> SavedQuranPagesFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}