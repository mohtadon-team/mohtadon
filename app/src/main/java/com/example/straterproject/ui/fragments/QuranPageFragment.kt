package com.example.straterproject.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.example.straterproject.databinding.FragmentQuranPageBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.QuranViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class QuranPageFragment(private val pageNumber: Int) : BaseFragment<FragmentQuranPageBinding>() {
    override val layoutFragmentId: Int= com.example.straterproject.R.layout.fragment_quran_page
    override val viewModel: QuranViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(com.example.straterproject.R.layout.fragment_quran_page, container, false)
        return rootView
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quranImageView = view.findViewById<ImageView>(com.example.straterproject.R.id.quranImageView)
        val quranPage:Int = viewModel.getQuranImageByPageNumber(requireContext(),pageNumber)
        quranImageView.setImageResource(quranPage)

    }
}