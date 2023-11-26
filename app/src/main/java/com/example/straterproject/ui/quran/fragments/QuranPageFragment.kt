package com.example.straterproject.ui.quran.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.quran.PageData
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentQuranPageBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.viewModels.QuranViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class QuranPageFragment: BaseFragment<FragmentQuranPageBinding>() {
    override val layoutFragmentId: Int= com.example.straterproject.R.layout.fragment_quran_page
    override val viewModel: QuranViewModel by viewModels()

    companion object {
        var pageData = PageData()
        var POSITION_ARG = "postion_arg"
        @JvmStatic
        fun newInstance(pageNumber: Int) =
            QuranPageFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION_ARG, pageNumber)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_quran_page, container, false)
        return rootView
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pageNumber = arguments?.getInt(POSITION_ARG)
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                pageData = viewModel.getSoraName(pageNumber!!)
            }
            Log.d("page number", "onViewCreated: " + pageNumber)
            Log.d("data for page", "onViewCreated: " + pageData)
            QuranViewPagerFragment.mBinding.quranHeader.headerTitle.text = pageData.soraName
        }
//        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
//            pageData = viewModel.getSoraName(pageNumber!!)
//            Log.d("page number", "onViewCreated: "+pageNumber)
//            Log.d("data for page", "onViewCreated: "+ pageData)
//            QuranViewPagerFragment.mBinding.quranHeader.headerTitle.text = pageData.soraName
//        }

        val quranImageView = view.findViewById<ImageView>(com.example.straterproject.R.id.quranImageView)
        val quranPage: Drawable? = viewModel.getQuranImageByPageNumber(requireContext(),pageNumber!!)
        quranImageView.setImageDrawable(quranPage)

       }
    }
