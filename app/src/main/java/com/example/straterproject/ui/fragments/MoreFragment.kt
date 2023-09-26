package com.example.straterproject.ui.fragments

import android.annotation.SuppressLint
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.straterproject.R
import com.example.straterproject.databinding.FragmentMoreBinding
import com.example.straterproject.ui.base.BaseFragment
import java.util.*

@SuppressLint("InlinedApi")
class MoreFragment : BaseFragment<FragmentMoreBinding>() {
    override val layoutFragmentId: Int=R.layout.fragment_more

    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)}

    }