package com.example.straterproject.ui.hadith.hadithDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentDuaaDetailsBinding
import com.example.straterproject.databinding.FragmentHadithDetailsBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.duaa.duaaDetails.DuaaDetailsFragmentArgs
import com.example.straterproject.ui.duaa.duaaDetails.DuaaDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithDetailsFragment : BaseFragment<FragmentHadithDetailsBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_hadith_details
    override val viewModel: HadithDetailsViewModel by viewModels()
    private val args: HadithDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.hadithText.text = args.hadithText

    }

}