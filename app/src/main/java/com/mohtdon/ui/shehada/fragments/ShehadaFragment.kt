package com.mohtdon.ui.shehada.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentShehadaBinding
import com.mohtdon.ui.base.BaseFragment

class ShehadaFragment : BaseFragment<FragmentShehadaBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val layoutFragmentId: Int= R.layout.fragment_shehada
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pageHeader.headerTitle.text = "الشهاده"
        binding.pageHeader.search.visibility = View.GONE
    }

}