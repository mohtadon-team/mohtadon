package com.mohtdon.ui.azkar.azkarDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentAzkarListBinding
import com.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarDetailsFragment : BaseFragment<FragmentAzkarListBinding>() {
    override val layoutFragmentId: Int = R.layout.fragment_azkar_list
    override val viewModel: AzkarDetailsViewModel by viewModels()
    private val args: AzkarDetailsFragmentArgs by navArgs()
    private lateinit var azkarDetailsRvAdapter: AzkarDetailsRvAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter(args.position)

    }

    private fun setAdapter(position: Int) {
        val azkarDetailsItems = viewModel.getSpecificAzkarDetails(position)
        azkarDetailsRvAdapter = AzkarDetailsRvAdapter(azkarDetailsItems)
        binding.azkarListRv.adapter = azkarDetailsRvAdapter
    }
}