package com.example.straterproject.ui.hadith.hadithCategory

import android.content.Context
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HadithCategoryViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {

    private val _hadithCategoryUiState = MutableStateFlow(HadithCategoryUiState())
    val hadithCategoryUiState = _hadithCategoryUiState.asStateFlow()

    init {
        collectHadithListData()
    }

    private fun collectHadithListData() {

        _hadithCategoryUiState.update {
            it.copy(
                hadithCategoryList = arrayListOf("احاديث الصلاة ", "احاديث الصيام")
            )
        }
    }
}