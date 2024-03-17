package com.mohtdon.ui.quran.search.viewModels

import com.mohtdon.data.dataSource.local.QuranRepositoryImpl
import com.mohtdon.domain.models.quran.Aya
import com.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuranSearchViewModel @Inject constructor
    (private  val repository: QuranRepositoryImpl): BaseViewModel() {
     suspend fun getSearchResult(key: String): List<Aya> {
        return repository.getAyaBySubText(key)
    }

}