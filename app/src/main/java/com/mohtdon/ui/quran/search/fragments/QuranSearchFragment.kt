package com.mohtdon.ui.quran.search.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohtdon.domain.models.quran.Aya
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQuranSearchBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.quran.search.adapter.IoClickSearchItem
import com.mohtdon.ui.quran.search.adapter.QuranSearchAdapter
import com.mohtdon.ui.quran.search.viewModels.QuranSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuranSearchFragment : BaseFragment<FragmentQuranSearchBinding>(), IoClickSearchItem {
    override val layoutFragmentId: Int = R.layout.fragment_quran_search
    override val viewModel: QuranSearchViewModel by viewModels()
    private lateinit var ayaSearchList: ArrayList<Aya>

    private lateinit var searchAdapter: QuranSearchAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchRecycler = view.findViewById<RecyclerView>(R.id.search_recycler)
        searchAdapter = QuranSearchAdapter(this)
        binding.header.headerTitle.visibility=View.GONE
        binding.header.search.visibility=View.GONE
        binding.header.quranSearchEt.visibility=View.VISIBLE
        binding.header.quranSearchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                GlobalScope.launch(Dispatchers.Main) {
                    ayaSearchList = viewModel.getSearchResult(s.toString()) as ArrayList<Aya>
                    searchAdapter.updateListSearchResult(ayaSearchList)
                    searchRecycler.layoutManager = LinearLayoutManager(requireContext())
                    searchRecycler.adapter = searchAdapter
                    searchRecycler.setHasFixedSize(true)
                    Log.d("search size", "onTextChanged: " + ayaSearchList.size)

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }

    override fun onSearchItemClick(aya: Aya) {
        val action =
            QuranSearchFragmentDirections.actionQuranSearchFragmentToQuranViewPagerFragment(aya.page)
        findNavController().navigate(action)
    }
}


