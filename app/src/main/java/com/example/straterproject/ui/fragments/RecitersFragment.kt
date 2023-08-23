package com.example.straterproject.ui.fragments


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.dataSource.remote.response.reciters.Reciter
import com.example.domain.entity.reciters.ReciterEntity
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentRecitersBinding
import com.example.straterproject.ui.UiState

import com.example.straterproject.ui.adapters.RecitersAdapter
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.RecitersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecitersFragment : BaseFragment<FragmentRecitersBinding>()  {
    override val layoutFragmentId= R.layout.fragment_reciters
    override val viewModel : RecitersViewModel by viewModels()

    private lateinit var recitersAdapter: RecitersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recitersAdapter = RecitersAdapter(requireContext())

        binding.recitersRv.apply {
            adapter = recitersAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        viewModel.reciters.observe(viewLifecycleOwner){
            handleStae(it)
        }

    }

    private fun handleStae(state: UiState<List<ReciterEntity>>?) {
       when(state){
           is UiState.Error -> {
               Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
           }
           is UiState.Success -> {
               recitersAdapter.reciters = state.data!!
           }
           UiState.Loading -> {
               Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
           }
           null -> {
               Toast.makeText(requireContext(), "something happend", Toast.LENGTH_SHORT).show()
           }
       }
    }



}