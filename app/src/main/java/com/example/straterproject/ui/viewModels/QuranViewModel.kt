package com.example.straterproject.ui.viewModels

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.data.dataSource.local.QuranRepositoryImpl
import com.example.data.dataSource.remote.response.quran.models.PagesManager
import com.example.domain.models.quran.PageData
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuranViewModel  @Inject constructor
    (private  val repository: QuranRepositoryImpl): BaseViewModel()  {
    fun getQuranImageByPageNumber(context: Context, pageNumber: Int): Drawable? {
        return PagesManager.getQuranImageByPageNumber(context, pageNumber)
    }
    suspend fun getSoraName(pageNum: Int): PageData {
        return repository.getSoraName(pageNum)
    }
}