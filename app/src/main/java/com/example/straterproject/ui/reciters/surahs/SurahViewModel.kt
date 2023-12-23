package com.example.straterproject.ui.reciters.surahs

import androidx.lifecycle.SavedStateHandle
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.MoshafEnitity
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.ui.radio.RadioUiSate
import com.example.straterproject.utilities.suraMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SurahViewModel  @Inject constructor(
   private val savedStateHandle: SavedStateHandle
):BaseViewModel(){

    private var _uiState = MutableStateFlow(SurahUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val moshaf =  savedStateHandle.get<MoshafEnitity>("moshaf")
        val surahList = moshaf?.surah_list?.split(',')?.map { suraMap[it] } ?: emptyList()
        _uiState.update {
            it.copy(
                surahList = surahList , reciterInfo = moshaf?.reciterName ?: "" , moshaf = moshaf!!
            )
        }
    }
    fun onSearchTextChange(text : CharSequence) {
//        _uiState.update { it.copy(searchText = text.toString()) }
//        searchInRadio(text)
    }

    fun onCloseClick() {
       // _uiState.update { it.copy(isTabSearchVisible = false , isTabTitleVisible = true ,searchText= "") }
    }

    fun onBackClick() {
     //   sendUiEffect(RadioUiEffect.Back)
    }

    fun onSearchClick()  {
//        _uiState.update {
//            it.copy(isTabSearchVisible = true , isTabTitleVisible = false)
//        }
    }

//    private fun sendUiEffect(uiEffect: RadioUiEffect) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _uiEffect.emit(uiEffect)
//        }
//    }

}