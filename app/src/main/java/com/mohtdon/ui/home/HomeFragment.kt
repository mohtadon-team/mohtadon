package com.mohtdon.mohtdon.ui.home

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohtdon.domain.usecases.GetAyaByIdUseCase
import com.mohtdon.domain.usecases.GetMonthPrayerTimesUseCase
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentHomeBinding
import com.mohtdon.mohtdon.ui.base.BaseFragment
import com.mohtdon.mohtdon.ui.home.adapters.HomeRvAdapter
import com.mohtdon.mohtdon.ui.home.adapters.HomeTodayThingsRvAdapter
import com.mohtdon.mohtdon.ui.home.adapters.OnHomeRvItemListener
import com.mohtdon.mohtdon.ui.home.rvitems.HomeTodayThingsRvItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnHomeRvItemListener {
    override val layoutFragmentId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var homeRvAdapter: HomeRvAdapter
    private lateinit var homeTodayThingsRvAdapter: HomeTodayThingsRvAdapter


    @Inject
    lateinit var getAyaByIdUseCase: GetAyaByIdUseCase

    @Inject
    lateinit var getMonthPrayerTimesUseCase: GetMonthPrayerTimesUseCase


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.viewModel  = viewModel


        setAdapter()
        setTodayThingsData()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTodayThingsData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homeUiState.collect {
                homeTodayThingsRvAdapter.setItems(
                    mutableListOf(
                        HomeTodayThingsRvItem(
                            "دعاء اليوم", it.randomDuaa
                        ),

                        HomeTodayThingsRvItem(
                            "ايه اليوم", it.randomAya

                        )
                    )

                )
            }
        }
        homeTodayThingsRvAdapter.notifyDataSetChanged()

    }

    private fun setAdapter() {
        homeRvAdapter = HomeRvAdapter(this)
        homeTodayThingsRvAdapter = HomeTodayThingsRvAdapter()
        binding.homeNavigationItemsRv.adapter = homeRvAdapter
        binding.homeRvTodayTings.adapter = homeTodayThingsRvAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemclick(position: Int) {
        when (position) {
            0 -> {

            }

            1 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAzkarListFragment())
            }

            2 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDuaaListFragment())
            }

            3 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHadithCategoryFragment())
            }

            4 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAyatListFragment())
            }

            5 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMoreFragment())
            }

            6 -> {

            }
        }
    }

}



