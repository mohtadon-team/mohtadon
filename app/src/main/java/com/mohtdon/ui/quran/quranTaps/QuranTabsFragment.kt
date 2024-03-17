package com.mohtdon.ui.quran.quranTaps

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mohtdon.ui.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQuranTabsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuranTabsFragment : BaseFragment<FragmentQuranTabsBinding>(),
    TabLayout.OnTabSelectedListener {
    override val layoutFragmentId: Int = R.layout.fragment_quran_tabs
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
        binding.commonHeader.search.setOnClickListener {
            findNavController().navigate(R.id.quranSearchFragment)
        }
        val name = sharedPreferences.getString("suraName", "لم تقم بالقراءة بعد ").toString()
        binding.lastReadInfo.text =
            "سورة " + name + " | صفحة رقم " + sharedPreferences.getInt("last_page", 0)

        binding.continueReadingText.setOnClickListener {
            setInt("last_page_flag", 1)
            val action =
                QuranTabsFragmentDirections.actionQuranTabsFragmentToQuranViewPagerFragment(0)
            findNavController().navigate(action)
        }
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
        editor.apply()
    }

    private fun setUpTabLayout() {
        val adapter = QuranTabsAdapter(this)
        binding.quranTabsViewpager.adapter = adapter
        TabLayoutMediator(
            binding.fragQuranTabsTablayout, binding.quranTabsViewpager
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