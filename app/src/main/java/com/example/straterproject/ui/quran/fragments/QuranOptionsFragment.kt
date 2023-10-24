package com.example.straterproject.ui.quran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranOptionsBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranOptionsFragment : BaseFragment<FragmentQuranOptionsBinding>() {
    override val layoutFragmentId: Int=R.layout.fragment_quran_options
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.quranReadingCard.setOnClickListener {
            findNavController().navigate(R.id.quranTabsFragment)
        }
        binding.quranListeningCard.setOnClickListener {
            findNavController().navigate(R.id.recitersFragment)
        }

        binding.commonHeader.search.visibility=View.GONE
    }
}