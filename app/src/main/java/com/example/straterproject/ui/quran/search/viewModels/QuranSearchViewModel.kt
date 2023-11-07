package com.example.straterproject.ui.quran.search.viewModels

import com.example.data.dataSource.local.QuranRepositoryImpl
import com.example.domain.models.Aya
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuranSearchViewModel @Inject constructor
    (private  val repository: QuranRepositoryImpl): BaseViewModel() {
     suspend fun getSearchResult(key: String): List<Aya> {
        return repository.getAyaBySubText(key)
    }

}