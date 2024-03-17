package com.mohtdon.ui.quran

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQuranOptionsBinding
import com.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuranOptionsFragment : BaseFragment<FragmentQuranOptionsBinding>() {
    override val layoutFragmentId: Int= R.layout.fragment_quran_options
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