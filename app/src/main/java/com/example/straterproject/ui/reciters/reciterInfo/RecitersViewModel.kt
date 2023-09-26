package com.example.straterproject.ui.reciters.reciterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.reciters.ReciterEntity
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecitersViewModel @Inject constructor(
    private val getAllReciterUseCase: GetAllReciterUseCase,
    private val savedStateHandle: SavedStateHandle
):BaseViewModel() {

    private var _reciters = MutableLiveData<UiState<List<ReciterEntity>>>()
    val  reciters : LiveData<UiState<List<ReciterEntity>>> = _reciters

    init{ getAllReciter() }

    private fun getAllReciter() = viewModelScope.launch {
        try {

            _reciters.postValue(UiState.Loading)
            val result = getAllReciterUseCase.invoke()
            _reciters.postValue(UiState.Success(result))
            savedStateHandle["Loading"] = result

        }catch (e:Exception){
            _reciters.postValue(UiState.Error(e.message.toString()))
        }
    }

     fun searchInReciterList(query :CharSequence) {
        val x =  savedStateHandle.get<List<ReciterEntity>>("Loading")
        val filteredList = x?.filter { it.name.contains(query) }
         _reciters.postValue(UiState.Success(filteredList))

    }
}