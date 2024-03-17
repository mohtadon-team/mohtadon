package com.mohtdon.ui.quran.quranList

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.mohtdon.domain.models.quran.Surah
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentQuranListBinding
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.quran.interfaces.IonSurahClick
import com.mohtdon.ui.quran.quranTaps.QuranTabsFragmentDirections
import com.mohtdon.ui.viewModels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class QuranListFragment : BaseFragment<FragmentQuranListBinding>(), IonSurahClick {

    override val layoutFragmentId: Int = R.layout.fragment_quran_list
    override val viewModel: SurahViewModel by viewModels()

    private lateinit var quranListAdapter: QuranListAdapter

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quranListAdapter = QuranListAdapter(this)

        lifecycleScope.launch {
            viewModel.allSura.collect{
                quranListAdapter.setSurahList(it)

                Log.i("ahmed", it.get(113).toString())
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.surahRec.layoutManager = LinearLayoutManager(requireContext())
        binding.surahRec.adapter = quranListAdapter
    }


    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
        editor.apply()
    }

    override fun clickSurah(surah: Surah) {
        val pagesRange = surah.pages
        val startPage = pagesRange[0]
        Log.d("startPage", "clickSurah: " + pagesRange[0])
        setInt("last_page_flag", 0)

        val action = QuranTabsFragmentDirections.actionQuranTabsFragmentToQuranViewPagerFragment(startPage)
        findNavController().navigate(action)
    }
}
