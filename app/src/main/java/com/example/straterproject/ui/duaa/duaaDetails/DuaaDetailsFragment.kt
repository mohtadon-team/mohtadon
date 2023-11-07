package com.example.straterproject.ui.duaa.duaaDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentAzkarListBinding
import com.example.straterproject.databinding.FragmentDuaaDetailsBinding
import com.example.straterproject.databinding.FragmentDuaaListBinding
import com.example.straterproject.ui.azkar.azkarDetails.AzkarDetailsFragmentArgs
import com.example.straterproject.ui.azkar.azkarDetails.AzkarDetailsRvAdapter
import com.example.straterproject.ui.azkar.azkarDetails.AzkarDetailsViewModel
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuaaDetailsFragment : BaseFragment<FragmentDuaaDetailsBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_duaa_details
    override val viewModel: DuaaDetailsViewModel by viewModels()
    private val args: DuaaDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setDuaaText(args.position)

    }

    private fun setDuaaText(position: Int) {
        val duaaText = viewModel.getDuaaText(position)
        binding.duaaText.text = duaaText
    }
}