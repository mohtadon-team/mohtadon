package com.example.straterproject.ui.duaa.duaaList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentDuaaListBinding
import com.example.straterproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuaaListFragment : BaseFragment<FragmentDuaaListBinding>(), OnDuaaListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_duaa_list
    override val viewModel: DuaaListViewModel by viewModels()

    private lateinit var duaaListRvAdapter: DuaaListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter()

    }

    private fun setAdapter() {
        val duaaItems = viewModel.duaaListUiState.value.duaaList
        duaaListRvAdapter = DuaaListRvAdapter(this, duaaItems)
        binding.duaaListRv.adapter = duaaListRvAdapter
    }

    override fun onItemclick(position: Int) {
        findNavController().navigate(
            DuaaListFragmentDirections.actionDuaaListFragmentToDuaaDetailsFragment(
                position
            )
        )
    }
}