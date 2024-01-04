package com.example.mohtdon.ui.quran.quranPage

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mohtdon.databinding.FragmentQuranPageBinding
import com.example.mohtdon.ui.base.BaseFragment
import com.example.mohtdon.ui.viewModels.QuranViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.io.InputStream


@AndroidEntryPoint
class QuranPageFragment constructor() : BaseFragment<FragmentQuranPageBinding>() {
    override val layoutFragmentId: Int = com.example.mohtdon.R.layout.fragment_quran_page
    override val viewModel: QuranViewModel by viewModels()

    private var pageNumber: Int? = 0

    constructor(pageNumber: Int) : this() {
        this.pageNumber = pageNumber
    }


    fun getQuranImageByPageNumber(pageNumber: Int): Drawable? {
        var drawableName: String = ""
        if (pageNumber < 10) {
            drawableName = "quran/images/page00$pageNumber.png"

        } else if (pageNumber in 10..99) {
            drawableName = "quran/images/page0$pageNumber.png"

        } else {
            drawableName = "quran/images/page$pageNumber.png"
        }
        var istr: InputStream? = null
        try {
            istr = requireContext().assets.open(drawableName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Drawable.createFromStream(istr, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        binding.quranImageView.setImageDrawable(
            getQuranImageByPageNumber(
                pageNumber ?: 1
            )
        )


    }


}

