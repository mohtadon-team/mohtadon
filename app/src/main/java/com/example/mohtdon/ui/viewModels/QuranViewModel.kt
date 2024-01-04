package com.example.mohtdon.ui.viewModels

import com.example.data.dataSource.local.QuranRepositoryImpl
import com.example.domain.models.quran.PageData
import com.example.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuranViewModel  @Inject constructor
    (private  val repository: QuranRepositoryImpl): BaseViewModel()  {

    suspend fun getSoraName(pageNum: Int): PageData {
        return repository.getSoraName(pageNum)
    }
}