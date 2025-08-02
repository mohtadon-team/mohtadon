package com.mohtdon.ui.sebha.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentRosaryBinding
import com.mohtdon.ui.base.BaseFragment


class RosaryFragment : BaseFragment<FragmentRosaryBinding>() {
    private var counter = 0
    override val layoutFragmentId: Int= R.layout.fragment_rosary
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pageHeader.headerTitle.text = "السبحه"
        binding.pageHeader.search.visibility = View.GONE
        binding.counterTv.setOnClickListener {
            counter++
            binding.counterTv.text = counter.toString()
            if (binding.counterTxt.hasFocus()) {
                binding.counterTxt.clearFocus()
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.counterTxt.windowToken, 0)
            }
        }
        binding.resetBtn.setOnClickListener {
            counter = 0
            binding.counterTv.text="0"
        }
    }


}