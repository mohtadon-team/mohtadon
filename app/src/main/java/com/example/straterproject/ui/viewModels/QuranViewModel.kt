package com.example.straterproject.ui.viewModels

import android.content.Context
import com.example.data.dataSource.remote.response.quran.models.PagesManager
import com.example.straterproject.ui.base.BaseViewModel

class QuranViewModel : BaseViewModel() {
    fun getQuranImageByPageNumber(context: Context, pageNumber: Int): Int {
        return PagesManager.getQuranImageByPageNumber(context, pageNumber)
    }

}