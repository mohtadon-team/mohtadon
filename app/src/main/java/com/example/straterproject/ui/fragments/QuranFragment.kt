package com.example.straterproject.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Surah
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranBinding
import com.example.straterproject.ui.adapters.QuranAdapter
import com.example.straterproject.ui.adapters.QuranPagerAdapter
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.interfaces.IonSurahClick
import com.example.straterproject.ui.viewModels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuranFragment : BaseFragment<FragmentQuranBinding>() ,IonSurahClick{

    override val layoutFragmentId: Int = R.layout.fragment_quran
    override val viewModel: SurahViewModel by viewModels()

    private lateinit var quranAdapter: QuranAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quranAdapter = QuranAdapter()
        quranAdapter.ionSurahClick=this
        setupRecyclerView()
        observeSurahList()
        viewModel.fetchSurah()
        onClickActions()
    }

    private fun onClickActions() {
//
//        binding.commonHeader.search_view.setOnSearchClickListener{
//            binding.commonHeader.header_title.visibility=View.GONE
//
//        }
//       binding.commonHeader.search_view?.setOnCloseListener {
//           binding.commonHeader.header_title.visibility=View.VISIBLE
//           return@setOnCloseListener false
//       }
//        binding.commonHeader.search_view?.setOnQueryTextListener(object:SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                return  false
//            }
//
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                filterList(p0)
//                return true
//            }
//
//        }
//        )
    }

    private fun filterList(query: String?) {
            val filteredList = if (query.isNullOrEmpty()) {
                viewModel.items.value ?: emptyList()
            } else {
                viewModel.items.value?.filter { surah ->
                    surah.nameArabic!!.contains(query, ignoreCase = true)
                } ?: emptyList()
            }

            quranAdapter.setSurahList(filteredList)
        }



    private fun setupRecyclerView() {
        binding.surahRec.layoutManager = LinearLayoutManager(requireContext())
        binding.surahRec.adapter = quranAdapter
    }

    private fun observeSurahList() {
        viewModel.items.observe(viewLifecycleOwner) { surahList ->
            quranAdapter.setSurahList(surahList)
        }
    }

    override fun clickSurah(surah: Surah) {
        val pagesRange = surah.pages
       val startPage=pagesRange[0]
        Log.d("startPage", "clickSurah: "+pagesRange[0])
        val action = QuranFragmentDirections.actionQuranFragmentToQuranViewPagerFragment(startPage)
        findNavController().navigate(action)
       Toast.makeText(requireContext(),"surah  :$surah",Toast.LENGTH_SHORT).show()
    }
}
