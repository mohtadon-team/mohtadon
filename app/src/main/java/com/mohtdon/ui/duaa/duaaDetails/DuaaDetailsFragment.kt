package com.mohtdon.ui.duaa.duaaDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentDuaaDetailsBinding
import com.mohtdon.ui.base.BaseFragment
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