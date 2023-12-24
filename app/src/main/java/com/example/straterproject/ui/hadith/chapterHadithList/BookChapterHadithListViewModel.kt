package com.example.straterproject.ui.hadith.chapterHadithList

import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetSpecificBookChapterHadithUseCase
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.hadith.hadithBookChapters.HadithBookChaptersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookChapterHadithListViewModel @Inject constructor(
    private val getSpecificBookChapterHadithUseCase: GetSpecificBookChapterHadithUseCase
) : BaseViewModel() {
    private val _chapterHadaithList = MutableStateFlow(ArrayList<String>())
    val chapterHadaithList = _chapterHadaithList.asStateFlow()


    fun collectSpecificChapterHadith(bookName:String , chapterNumber:Int  ) {
        viewModelScope.launch {
            val bookChapters = getSpecificBookChapterHadithUseCase.invoke(bookName , chapterNumber)
            _chapterHadaithList.update {
                getSpecificBookChapterHadithUseCase.invoke(bookName , chapterNumber).bookChapters
            }
        }
    }
}