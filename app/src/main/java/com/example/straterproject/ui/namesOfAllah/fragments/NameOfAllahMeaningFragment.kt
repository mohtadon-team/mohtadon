package com.example.straterproject.ui.namesOfAllah.fragments

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentNameOfAllahMeaningBinding
import com.example.straterproject.ui.base.BaseFragment
import com.example.straterproject.ui.namesOfAllah.adapters.NamesGridViewAdapter
import com.example.straterproject.ui.namesOfAllah.viewModels.NameMeaningViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NameOfAllahMeaningFragment : BaseFragment<FragmentNameOfAllahMeaningBinding>() {
    override val layoutFragmentId: Int=R.layout.fragment_name_of_allah_meaning
    override val viewModel: NameMeaningViewModel by viewModels()
    private val args: NameOfAllahMeaningFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var name=args.name
        binding.pageHeader.headerTitle.text=name
        binding.pageHeader.search.visibility=View.GONE
        binding.nameTitle.text=name
        binding.meaningTxt.text= viewModel.getMeaning(name)
    }


}