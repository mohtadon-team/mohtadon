package com.example.straterproject.ui.hadith.hadithList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHadithListBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.hadith.hadithDetails.HadithDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HadithListFragment : BaseFragment<FragmentHadithListBinding>(), OnHadithListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_hadith_list
    override val viewModel: HadithListViewModel by viewModels()
    private val args: HadithListFragmentArgs by navArgs()

    private lateinit var hadithListRvAdapter: HadithListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setAdapter()

    }

    private fun setAdapter() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hadithListUiState.collect {
                when (args.position) {
                    0 -> {
                        val hadithItems = viewModel.hadithListUiState.value.prayersHadithList
                        hadithListRvAdapter = HadithListRvAdapter(this@HadithListFragment, hadithItems)
                        binding.hadithListRv.adapter = hadithListRvAdapter
                    }
                    1 -> {
                        val hadithItems = viewModel.hadithListUiState.value.fastingHadithList
                        hadithListRvAdapter = HadithListRvAdapter(this@HadithListFragment, hadithItems)
                        binding.hadithListRv.adapter = hadithListRvAdapter
                    }
                }
            }
        }
    }

    override fun onItemclick(hadithText: String) {
        findNavController().navigate(
            HadithListFragmentDirections.actionHadithListFragmentToHadithDetailsFragment(
                hadithText
            )
        )
    }
}