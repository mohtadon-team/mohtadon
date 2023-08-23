package com.example.straterproject.ui.fragments


import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranFragment : BaseFragment<FragmentQuranBinding>() {
    override val layoutFragmentId: Int=R.layout.fragment_quran
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


}