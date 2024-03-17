package com.mohtdon.ui.tafseer.surahList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentSurahListBinding
import com.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SurahListFragment : BaseFragment<FragmentSurahListBinding>(), OnAyaListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_surah_list
    override val viewModel: SurahListViewModel by viewModels()

    lateinit var surahListRvAdapter: SurahListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter()

    }

    private fun setAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
             viewModel.surahListUiState.collect{
                surahListRvAdapter = SurahListRvAdapter(this@SurahListFragment, it.surahList)
                binding.surahListRv.adapter = surahListRvAdapter
            }
        }
    }

    override fun onItemclick(position: Int) {
        findNavController().navigate(
            SurahListFragmentDirections.actionSurahListFragmentToAyaTafseerFragment(
                position
            )
        )
    }
}