package com.example.straterproject.ui.tafseer.surahList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentSurahListBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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