package com.example.straterproject.ui.quran.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.straterproject.R
import com.example.straterproject.ui.quran.adapters.QuranPagerAdapter
import io.hussienfahmy.mefab.MovableExpandedFloatingActionButton
import io.hussienfahmy.mefab.fabs.OnEdgeFabClickListener


class QuranViewPagerFragment : Fragment() {

    private val args: QuranViewPagerFragmentArgs by navArgs()
    private val sharedPreferencesFileName = "QuranPagePrefs"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_quran_view_pager, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val quranPagesAdapter = QuranPagerAdapter(requireActivity())
        val startPage: Int = args.startPageNum
        viewPager.adapter = quranPagesAdapter
        //set current item
        viewPager.currentItem = 604 - startPage



        val sharedPreferences =
            requireActivity().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)
        val saveButton = view.findViewById<TextView>(R.id.saveBtn)
        saveButton.setOnClickListener {
            saveButton.setBackgroundResource(R.drawable.filled_save_icon)
            // Save the current page number to SharedPreferences
            val editor = sharedPreferences.edit()
            val currentPageNum=604 - viewPager.currentItem
            editor.putInt("currentPageNumber", currentPageNum)
            editor.apply()
            Log.d("currentPageNumber", "Num" + currentPageNum)
        }


        val showsaveButton = view.findViewById<TextView>(R.id.goTosavedBtn)
        showsaveButton.setOnClickListener {
            val savedPage = sharedPreferences.getInt("currentPageNumber", -1)
            Log.d("savedPage", "P"+savedPage)
            saveButton.setBackgroundResource(R.drawable.filled_save_icon)
            viewPager.currentItem=604-savedPage
        }
       val searchTxt=view.findViewById<TextView>(R.id.search_with_aya)
        searchTxt.setOnClickListener {
            findNavController().navigate(R.id.quranSearchFragment)
        }
        val fabmenu=view.findViewById<MovableExpandedFloatingActionButton>(R.id.me_fab)
        fabmenu.setOnEdgeFabClickListener(OnEdgeFabClickListener { id ->
            Toast.makeText(
                context,
                when (id) {
                    R.id.save_page -> "Add Clicked"
                    R.id.move_to_page -> "Check Mark Clicked"
                    R.id.search_in_quran -> "Clear Clicked"
                    else -> ""
                }, Toast.LENGTH_SHORT
            ).show()
        })
    }
}