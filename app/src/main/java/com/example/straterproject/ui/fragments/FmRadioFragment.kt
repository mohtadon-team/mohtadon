package com.example.straterproject.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentFmRadioBinding
import com.example.straterproject.ui.base.BaseFragment

class FmRadioFragment : BaseFragment<FragmentFmRadioBinding>() {
    override val layoutFragmentId= R.layout.fragment_fm_radio

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fm_radio, container, false)
    }

    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

}