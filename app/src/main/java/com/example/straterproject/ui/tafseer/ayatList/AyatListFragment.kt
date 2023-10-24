package com.example.straterproject.ui.tafseer.ayatList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentAyatListBinding
import com.example.straterproject.databinding.FragmentHadithListBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.hadith.hadithList.HadithListFragmentDirections
import com.example.straterproject.ui.hadith.hadithList.HadithListRvAdapter
import com.example.straterproject.ui.hadith.hadithList.HadithListViewModel
import com.example.straterproject.ui.hadith.hadithList.OnHadithListRvListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AyatListFragment : BaseFragment<FragmentAyatListBinding>(), OnAyaListRvListener {
    override val layoutFragmentId: Int = R.layout.fragment_ayat_list
    override val viewModel: AyatListViewModel by viewModels()

    lateinit var ayatListRvAdapter: AyatListRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        setAdapter()

    }

    private fun setAdapter() {
        var hadithItems = viewModel.ayaListUiState.value.ayatList
        ayatListRvAdapter = AyatListRvAdapter(this, hadithItems)
        binding.ayatListRv.adapter = ayatListRvAdapter
    }

    override fun onItemclick(position: Int) {
        findNavController().navigate(
            AyatListFragmentDirections.actionAyatListFragmentToAyaTafseerFragment(
                position
            )
        )
    }
}