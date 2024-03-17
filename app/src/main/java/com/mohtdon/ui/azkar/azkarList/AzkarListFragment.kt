package com.mohtdon.ui.azkar.azkarList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentAzkarListBinding
import com.mohtdon.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzkarListFragment : BaseFragment<FragmentAzkarListBinding>(), OnAzkarListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_azkar_list
    override val viewModel: AzkarListViewModel by viewModels()

    lateinit var azkarListRvAdapter: AzkarListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        setAdapter()

    }

    private fun setAdapter() {
        val azkarItems = viewModel.azkarListUiState.value.azkarList
        azkarListRvAdapter = AzkarListRvAdapter(this, azkarItems)
        binding.azkarListRv.adapter = azkarListRvAdapter
    }

    override fun onItemclick(position: Int) {
        findNavController().navigate(
            AzkarListFragmentDirections.actionAzkarListFragmentToAzkarDetailsFragment(
                position
            )
        )
    }
}