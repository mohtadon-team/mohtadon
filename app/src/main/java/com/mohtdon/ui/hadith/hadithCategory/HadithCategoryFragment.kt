package com.mohtdon.ui.hadith.hadithCategory

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentHadithCategoryBinding
import com.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithCategoryFragment : BaseFragment<FragmentHadithCategoryBinding>() ,
    OnHadithCategoryListener {
    override val layoutFragmentId: Int = R.layout.fragment_hadith_category
    override val viewModel: HadithCategoryViewModel by viewModels()


    private lateinit var hadithListRvAdapter: HadithCategoryRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter()

    }

    private fun setAdapter() {
        val hadithItems = viewModel.hadithCategoryUiState.value.hadithCategoryList
        hadithListRvAdapter = HadithCategoryRvAdapter(this, hadithItems)
        binding.hadithCategoryList.adapter = hadithListRvAdapter
    }


    override fun onHadithCategoryitemClick(position: Int) {
        findNavController().navigate(
            HadithCategoryFragmentDirections.actionHadithCategoryFragmentToHadithBookChaptersFragment(
                position
            )
        )
    }
}