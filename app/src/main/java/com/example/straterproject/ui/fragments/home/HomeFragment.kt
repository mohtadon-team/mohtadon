package com.example.straterproject.ui.fragments.home

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.usecases.GetAyaByIdUseCase
import com.example.domain.usecases.GetMonthPrayerTimesUseCase
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHomeBinding
import com.example.straterproject.player.AudioItem
import com.example.straterproject.ui.PlayerEvents
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.reciters.player.AudioItemPlayerViewModel
import com.example.straterproject.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnHomeRvItemListener {
    override val layoutFragmentId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    private val vm : AudioItemPlayerViewModel by viewModels()

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
        binding.viewModel = viewModel


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
//               findNavController().navigate(HomeFragmentDirections.())
            }

            1 -> {
//                HomeFragmentDirections.actionHomeFragmentToSurahsFragment()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQiblaFragment())
            }

            2 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDuaaListFragment())
            }

            3 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHadithListFragment())
            }

            4 -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAyatListFragment())
            }

            5 -> {

            }

            6 -> {

            }
        }
    }

}



