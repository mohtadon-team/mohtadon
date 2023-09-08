package com.example.straterproject.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.QuranResponse
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllReciterUseCase: GetAllReciterUseCase
    ) :BaseViewModel() {

    private var _reciters = MutableLiveData<UiState<QuranResponse>>()
    val  reciters : LiveData<UiState<QuranResponse>> = _reciters



    fun getAllReciter() = viewModelScope.launch {
         try {
             _reciters.postValue(UiState.Loading)
             val result = getAllReciterUseCase.invoke()
             _reciters.postValue(UiState.Success(result))

         }catch (e:Exception){
             _reciters.postValue(UiState.Error(e.message.toString()))
         }
    }

}