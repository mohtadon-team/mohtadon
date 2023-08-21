package com.example.straterproject.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Surah
import com.example.domain.usecases.GetAllSurahUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(private val getAllSurahUseCase: GetAllSurahUseCase)
    : BaseViewModel() {
    private val surahs = MutableLiveData<List<Surah>>()
    val items: LiveData<List<Surah>> = surahs

    fun fetchSurah() {
        viewModelScope.launch {
            try {
                val itemList = getAllSurahUseCase.invoke()
                surahs.value = itemList
            } catch (e: Exception) {
                // Handle error
            }
//            try {
//                surahs.postValue(UiState.Loading)
//                val itemList = getAllSurahUseCase.invoke()
//                surahs.postValue(UiState.Success(itemList))
//            } catch (e: Exception) {
//                // Handle error
//                surahs.postValue(UiState.Error(e.message.toString()))
//            }



        }
    }
}