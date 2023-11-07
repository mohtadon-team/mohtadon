package com.example.straterproject.ui.quran.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.data.dataSource.remote.response.quran.models.SavedPageModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentSavedQuranPagesBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.quran.adapters.SavedPagesAdapter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint


class SavedQuranPagesFragment : BaseFragment<FragmentSavedQuranPagesBinding>() {

    override val layoutFragmentId: Int = R.layout.fragment_saved_quran_pages
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")
    private lateinit var savedAdapter: SavedPagesAdapter
    lateinit var savedList: MutableList<SavedPageModel>

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedList = mutableListOf()
        savedList = getList()
        savedAdapter = SavedPagesAdapter(savedList)
        savedAdapter.notifyDataSetChanged()
        binding.quranSavedPagesList.layoutManager = LinearLayoutManager(requireContext())
        binding.quranSavedPagesList.adapter = savedAdapter

    }

    fun getList(): MutableList<SavedPageModel> {
        var arrayItems: MutableList<SavedPageModel> = mutableListOf()

        val serializedObject = sharedPreferences.getString("saved_page", null)

        if (serializedObject != null) {
            val gson = Gson()
            val type = object : TypeToken<List<SavedPageModel>>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        } else {
//            arrayItems = emptyList().toMutableList()
        }

        return arrayItems
    }


}