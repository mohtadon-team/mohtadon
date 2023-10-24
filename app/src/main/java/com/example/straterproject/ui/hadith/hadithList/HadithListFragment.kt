package com.example.straterproject.ui.hadith.hadithList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentHadithListBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class HadithListFragment : BaseFragment<FragmentHadithListBinding>(), OnHadithListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_hadith_list
    override val viewModel: HadithListViewModel by viewModels()

    lateinit var hadithListRvAdapter: HadithListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter()

    }

    private fun setAdapter() {
        var hadithItems = viewModel.hadithListUiState.value.hadithList
        hadithListRvAdapter = HadithListRvAdapter(this, hadithItems)
        binding.hadithListRv.adapter = hadithListRvAdapter
    }

    override fun onItemclick(position: Int) {
        findNavController().navigate(
            HadithListFragmentDirections.actionHadithListFragmentToHadithDetailsFragment(
                position
            )
        )
    }
}