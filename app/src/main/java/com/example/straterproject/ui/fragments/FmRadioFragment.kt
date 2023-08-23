package com.example.straterproject.ui.fragments


import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentFmRadioBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FmRadioFragment : BaseFragment<FragmentFmRadioBinding>() {
    override val layoutFragmentId= R.layout.fragment_fm_radio
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")

}