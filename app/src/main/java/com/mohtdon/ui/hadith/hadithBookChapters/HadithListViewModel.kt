package com.mohtdon.ui.hadith.hadithBookChapters

import androidx.lifecycle.viewModelScope
import com.mohtdon.domain.usecases.GetSpecificHadithBookChaptersUseCase
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.hadith.hadithBookChapters.HadithBookChaptersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadithListViewModel @Inject constructor(
    private val getSpecificHadithBookChaptersUseCase: GetSpecificHadithBookChaptersUseCase
) : BaseViewModel() {


    private val _hadithBookChaptersUiState = MutableStateFlow(HadithBookChaptersUiState())
    val hadithBookChaptersUiState = _hadithBookChaptersUiState.asStateFlow()


     fun collectSpecificHadithBookChapters(bookName:String ) {
        viewModelScope.launch {
            val bookChapters = getSpecificHadithBookChaptersUseCase.invoke(bookName)
            _hadithBookChaptersUiState.update {
                it.copy(
                    specificBookChaptersList = bookChapters.bookChapters
                )
            }
        }
    }

}