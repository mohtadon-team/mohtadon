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
    private var localeManager: LocaleManager? = null
    override val viewModel: ViewModel
        get() = TODO("Not yet implemented")



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            localeManager =
                requireActivity().getSystemService(Context.LOCALE_SERVICE) as LocaleManager
        }

        binding.engLangBtn.setOnClickListener {
            localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("en"))
        }
        binding.arabicLangBtn.setOnClickListener {
            localeManager?.applicationLocales = LocaleList(Locale.forLanguageTag("ar"))
        }
       binding.resetLangBtn.setOnClickListener {
            localeManager?.applicationLocales = LocaleList.getEmptyLocaleList()
        }
    }

@SuppressLint("SetTextI18n")
override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val language = when (localeManager?.applicationLocales?.toLanguageTags()) {
                "en" -> "English"
                "ar" -> "Arabic"
                else -> "Not Set"
            }
            Log.wtf("LANG", localeManager?.applicationLocales?.toLanguageTags())
            binding.resetLangBtn.text =
                "Current In-App Language: $language"
        }
    }

    }