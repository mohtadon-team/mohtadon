package com.mohtdon.ui.quran.savedQuranPages

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.mohtdon.data.dataSource.remote.response.quran.models.SavedPageModel
import com.mohtdon.ui.base.BaseFragment
import com.mohtdon.ui.quran.quranTaps.QuranTabsFragmentDirections
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.mohtdon.mohtdon.R
import com.mohtdon.mohtdon.databinding.FragmentSavedQuranPagesBinding
import com.mohtdon.ui.quran.savedQuranPages.SavedPagesAdapter
import com.mohtdon.ui.quran.savedQuranPages.onSaveItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint


class SavedQuranPagesFragment : BaseFragment<FragmentSavedQuranPagesBinding>(),
    onSaveItemClickListener {

    override val layoutFragmentId: Int = R.layout.fragment_saved_quran_pages
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRv()

    }

    private fun initializeRv() {
        val savedPagesList = getSavedPagesList()
        val savedSuraNamesList = getSavedSuraNameList()
        val savedAdapter =
            SavedPagesAdapter(savedPagesList, savedSuraNamesList, this@SavedQuranPagesFragment)
        binding.quranSavedPagesList.adapter = savedAdapter
    }

    fun getSavedPagesList(): MutableList<SavedPageModel> {
        var arrayItems: MutableList<SavedPageModel> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_page", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<SavedPageModel>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)

            return arrayItems
        }

        return arrayItems
    }

    fun getSavedSuraNameList(): MutableList<String> {
        var arrayItems: MutableList<String> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_sura_names", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)

            return arrayItems
        }

        return arrayItems
    }

    override fun onItemClick(pageNumber: Int) {
        val action =
            QuranTabsFragmentDirections.actionQuranTabsFragmentToQuranViewPagerFragment(pageNumber)
        findNavController().navigate(action)
    }


}