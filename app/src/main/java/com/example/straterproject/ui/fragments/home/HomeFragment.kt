package com.example.straterproject.ui.fragments.home

import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.azan.AlarmScheduler
import com.example.data.azan.AlarmScheduling
import com.example.domain.usecases.GetAyaByIdUseCase
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHomeBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), OnHomeRvItemListener {
    override val layoutFragmentId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var homeRvAdapter: HomeRvAdapter
    lateinit var homeTodayThingsRvAdapter: HomeTodayThingsRvAdapter


    @Inject
    lateinit var getAyaByIdUseCase: GetAyaByIdUseCase

//    private lateinit var  alarmScheduler: AlarmScheduler

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel



//        Log.i("ahmed", "onViewCreated: ")
//        lifecycleScope.launch {
//            Log.i("ahmed", getAyaByIdUseCase.invoke(5))
//        }
//        val randomAyaNumber = Random().nextInt(6348 - 1)
//        lifecycleScope.launch {
//            var randomAya = getAyaByIdUseCase.invoke(5)
//            Log.i("ahmed", randomAya)
//            }
//        Log.i("ahmed",    Random().nextInt(51 - 1).toString())

        setAdapter()
        setTodayThingsData()


//
//        alarmScheduler = AlarmScheduling()
//        val pendingIntent = alarmPendingIntent(prayerArabicName, requestCode)
//        alarmScheduler.schedule(alarmValue, pendingIntent, requireActivity().application)
    }

//    private fun alarmPendingIntent(prayerNameArabic: String, requestCode: Int): PendingIntent {
//        val intent =
//            Intent(requireActivity().application, AzanNotificationReceiver::class.java).apply {
//                putExtra(Constants.PRAYER_NAME, prayerNameArabic)
//            }
//
//        return PendingIntent.getBroadcast(
//            requireActivity().application, requestCode, intent,
//            PendingIntent.FLAG_MUTABLE
//        )
//    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setTodayThingsData() {
        var homeTodayThingsItems: ArrayList<HomeTodayThingsRvItem> =
            ArrayList<HomeTodayThingsRvItem>(2)
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
       when(position){
           0 ->{
//               findNavController().navigate(HomeFragmentDirections.())
           }
            1 -> {
//                HomeFragmentDirections.actionHomeFragmentToSurahsFragment()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAzkarListFragment())
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



