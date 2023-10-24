package com.example.straterproject.ui.quran.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranTabsBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.quran.adapters.QuranTabsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranTabsFragment : BaseFragment<FragmentQuranTabsBinding>(),
    TabLayout.OnTabSelectedListener {
    override val layoutFragmentId: Int = R.layout.fragment_quran_tabs
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
        binding.commonHeader.search.setOnClickListener {
            findNavController().navigate(R.id.quranSearchFragment)
        }
    }

    private fun setUpTabLayout() {
        val adapter = QuranTabsAdapter(this)
        binding.quranTabsViewpager.adapter = adapter
        TabLayoutMediator(
            binding.fragQuranTabsTablayout,
            binding.quranTabsViewpager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "السور"
                1 -> tab.text = "المرجعيات"
            }
        }.attach()

        binding.fragQuranTabsTablayout.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        when (tab?.position) {
            0 -> {

            }

            1 -> {

            }

        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}