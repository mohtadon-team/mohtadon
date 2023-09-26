package com.example.straterproject.ui.fragments.home

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHomeBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(

) {
    override val layoutFragmentId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel




//        binding.circularProgressIndicator.setMaxProgress(10000.0);
//        binding.circularProgressIndicator.setCurrentProgress(5000.0);
// or all at once
//        binding.circularProgressIndicator.setProgress(5000.0, 10000.0);

//        var INTERVAL_MSEC: Long = 500;
//        var timer = Timer();
//        var timerTask = object : TimerTask() {
//            override fun run() {
//                viewModel.refreshTheDifferenceBetweenCurrentAndNextSalahTime()
//            }
//        }
//        timer.scheduleAtFixedRate(timerTask, 0, INTERVAL_MSEC);
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onStart() {
//        super.onStart()
//        viewModel.refreshTheDifferenceBetweenCurrentAndNextSalahTime()
//    }
}



