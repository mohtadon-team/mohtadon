package com.example.straterproject.ui.quran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.straterproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint


class SavedQuranPagesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_quran_pages, container, false)
    }


}