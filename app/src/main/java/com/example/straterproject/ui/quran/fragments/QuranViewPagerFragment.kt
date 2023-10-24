package com.example.straterproject.ui.quran.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.data.dataSource.remote.response.quran.models.SavedPageModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranViewPagerBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.quran.adapters.QuranPagerAdapter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import io.hussienfahmy.mefab.MovableExpandedFloatingActionButton
import io.hussienfahmy.mefab.fabs.OnEdgeFabClickListener
import java.lang.reflect.Array
import java.lang.reflect.Array.set
import javax.inject.Inject

@AndroidEntryPoint
class QuranViewPagerFragment : BaseFragment<FragmentQuranViewPagerBinding>() {

    private val args: QuranViewPagerFragmentArgs by navArgs()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override val layoutFragmentId: Int=R.layout.fragment_quran_view_pager
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val quranPagesAdapter = QuranPagerAdapter(requireActivity())
        val startPage: Int = args.startPageNum
        viewPager.adapter = quranPagesAdapter
        //set current item
        viewPager.currentItem = 604 - startPage
        binding.quranHeader.saveMark.visibility=View.VISIBLE
        binding.quranHeader.saveMark.setOnClickListener {
            val currentPageNum = 604 - viewPager.currentItem
            if (getList() == null) {
                var list: List<SavedPageModel>
                list = mutableListOf()
                list.add(SavedPageModel(currentPageNum))
                setList("saved_page", list)
            } else {
                //size

                var mlist: MutableList<SavedPageModel> = getList()
                if (mlist.size < 10) {
                    mlist.add(SavedPageModel(currentPageNum))
                    setList("saved_page", mlist)
                }

            }
        }
    }

    fun <T> setList(key: String, list: List<T>) {

        val gson = Gson()
        val json = gson.toJson(list)
        set(key, json)
    }

    fun set(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
        editor.apply()
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