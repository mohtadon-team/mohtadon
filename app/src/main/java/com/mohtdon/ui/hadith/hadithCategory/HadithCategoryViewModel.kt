package com.mohtdon.ui.hadith.hadithCategory

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.hadith.hadithCategory.HadithCategoryUiState
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
                hadithCategoryList = arrayListOf(" صحيح البخارى " , " صحيح مسلم " , " سنن الترمذي " ,
                    " سنن أبي داود " ,  " سنن ابن ماجه " , " سنن النسائي " , " مشكاة المصابيح " , " مسند أحمد "
                    , " السلسلة الصحيحة ")
            )
        }
    }
}