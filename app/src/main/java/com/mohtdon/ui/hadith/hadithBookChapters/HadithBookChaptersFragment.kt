package com.mohtdon.ui.hadith.hadithBookChapters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentHadithBookChaptersBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.utilities.HadithBookNames
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HadithBookChaptersFragment : BaseFragment<FragmentHadithBookChaptersBinding>(),
    OnHadithListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_hadith_book_chapters
    override val viewModel: HadithListViewModel by viewModels()
    private val args: HadithBookChaptersFragmentArgs by navArgs()

    private lateinit var hadithBookChaptersRvAdapter: HadithBookChaptersRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setAdapter()

    }

    private fun setAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hadithBookChaptersUiState.collect {
                when (args.position) {
                    0 ->
                        initializeRv(0)


                    1 ->
                        initializeRv(1)


                    2 ->
                        initializeRv(2)


                    3 ->
                        initializeRv(3)


                    4 ->
                        initializeRv(4)


                    5 ->
                        initializeRv(5)


                    6 ->
                        initializeRv(6)


                    7 ->
                        initializeRv(7)

                    8 ->
                        initializeRv(8)

                }
            }
        }
    }

    override fun onItemclick(chapterNumber: Int ) {
        findNavController().navigate(
            HadithBookChaptersFragmentDirections.actionHadithListFragmentToHadithDetailsFragment(
                chapterNumber, HadithBookNames[args.position]
            )
        )
    }

    private fun initializeRv(position: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hadithBookChaptersUiState.collect {
                viewModel.collectSpecificHadithBookChapters(HadithBookNames[position])
                hadithBookChaptersRvAdapter = HadithBookChaptersRvAdapter(
                    this@HadithBookChaptersFragment, it.specificBookChaptersList
                )
                binding.hadithListRv.adapter = hadithBookChaptersRvAdapter
            }
        }
    }
}