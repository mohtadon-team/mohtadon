package com.example.straterproject.ui.sebha.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentRosaryBinding
import com.example.straterproject.ui.base.BaseFragment


class RosaryFragment : BaseFragment<FragmentRosaryBinding>() {
    private var counter = 0
    override val layoutFragmentId: Int=R.layout.fragment_rosary
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pageHeader.headerTitle.text = "السبحه"
        binding.pageHeader.search.visibility = View.GONE
        binding.counterTv.setOnClickListener {
            counter++
            binding.counterTv.text = counter.toString()
        }
        binding.resetBtn.setOnClickListener {
            binding.counterTv.text="0"
        }
    }


}