package com.example.straterproject.ui.radio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.domain.usecases.GetAllRadioStation
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RadioViewModel @Inject constructor(
    private val getAllRadioStation: GetAllRadioStation ,
   private val savedStateHandle: SavedStateHandle
): BaseViewModel() {


    private var _uiState = MutableStateFlow(RadioUiSate())
    val uiState = _uiState.asStateFlow()

    private var _uiEffect = MutableSharedFlow<RadioUiEffect>()
    val  uiEffect = _uiEffect.asSharedFlow()

    init {
        getAllRadioStation()
    }

    private fun getAllRadioStation() = viewModelScope.launch {
        try {
            val result = getAllRadioStation.invoke()
            _uiState.update {
                it.copy(radioStations = result ?: emptyList())
            }
            savedStateHandle["radioStations"] = result ?: emptyList()
        }catch (e:Exception){

        }
    }

    fun searchInRadio(query :CharSequence) = viewModelScope.launch {
            val x =  savedStateHandle.get<List<RadioEntity>>("radioStations")
            val filteredList = x?.filter { it.name.contains(query) } ?: emptyList()
             _uiState.update {
            it.copy(radioStations = filteredList  )
          }
   }
    fun onSearchTextChange(text : CharSequence) {
        _uiState.update { it.copy(searchText = text.toString()) }
        searchInRadio(text)
    }

    fun onCloseClick() {
        _uiState.update { it.copy(isTabSearchVisible = false , isTabTitleVisible = true ,searchText= "") }
    }

    fun onBackClick() {
        sendUiEffect(RadioUiEffect.Back)
    }

    fun onSearchClick()  {
        _uiState.update {
            it.copy(isTabSearchVisible = true , isTabTitleVisible = false)
        }
    }

    private fun sendUiEffect(uiEffect: RadioUiEffect) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEffect.emit(uiEffect)
        }
    }

}