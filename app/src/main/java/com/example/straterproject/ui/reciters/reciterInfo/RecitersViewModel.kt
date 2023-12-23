package com.example.straterproject.ui.reciters.reciterInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.domain.models.AppException
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.radio.RadioUiEffect
import com.example.straterproject.ui.radio.RadioUiSate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecitersViewModel @Inject constructor(
    private val getAllReciterUseCase: GetAllReciterUseCase,
    private val savedStateHandle: SavedStateHandle
):BaseViewModel() {

    private var _uiState = MutableStateFlow(ReciterUiSate())
    val uiState = _uiState.asStateFlow()

    private var _uiEffect = MutableSharedFlow<ReciterUiEffect>()
    val  uiEffect = _uiEffect.asSharedFlow()

    init{ getAllReciter() }

    private fun getAllReciter() = viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                val result = getAllReciterUseCase.invoke()
                _uiState.update { it.copy(reciters = result ?: emptyList() , isLoading = false , isDataExist = true) }

            }catch (e: AppException){
                _uiState.update { it.copy(isError = true , errorMessage = e.message.toString() , isLoading = false) }
            }
    }

     fun searchInReciterList(query :CharSequence) {
//         val x =  savedStateHandle.get<List<ReciterEntity>>("getAllReciter")
//         val filteredList = x?.filter { it.name.contains(query) } ?: emptyList()
//         _uiState.update {
//             it.copy(reciters = filteredList  )
//         }
    }

    fun onSearchTextChange(text : CharSequence) {
        _uiState.update { it.copy(  searchText = text.toString() ) }
        searchInReciterList(_uiState.value.searchText)
    }

    fun onCloseClick() {
        _uiState.update { it.copy(isTabSearchVisible = false , isTabTitleVisible = true , searchText = "" ) }

    }

    fun onBackClick() {
        sendUiEffect(ReciterUiEffect.Back)
    }

    fun onSearchClick()  {
        _uiState.update {
            it.copy(isTabSearchVisible = true , isTabTitleVisible = false)
        }
    }

    private fun sendUiEffect(uiEffect: ReciterUiEffect) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEffect.emit(uiEffect)
        }
    }
}