package com.example.straterproject.ui.shehada.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentShehadaBinding
import com.example.straterproject.ui.base.BaseFragment

class ShehadaFragment : BaseFragment<FragmentShehadaBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val layoutFragmentId: Int=R.layout.fragment_shehada
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pageHeader.headerTitle.text = "الشهاده"
        binding.pageHeader.search.visibility = View.GONE
    }

}