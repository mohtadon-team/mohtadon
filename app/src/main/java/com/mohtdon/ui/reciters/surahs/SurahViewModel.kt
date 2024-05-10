package com.mohtdon.ui.reciters.surahs

import androidx.lifecycle.SavedStateHandle
import com.mohtdon.domain.models.reciters.MoshafEnitity
import com.mohtdon.domain.models.reciters.ReciterEntity
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.quran.SurahUiEvent
import com.mohtdon.ui.reciters.reciterInfo.ReciterUiEffect
import com.mohtdon.ui.reciters.reciterInfo.ReciterUiSate
import com.mohtdon.utilities.suraMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SurahViewModel  @Inject constructor(
   private val savedStateHandle: SavedStateHandle
): BaseViewModel(){

    private var _uiState = MutableStateFlow(SurahUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllSurahNames()
    }

    private fun getAllSurahNames() {
        val moshaf = savedStateHandle.get<MoshafEnitity>("moshaf")
        val surahList = moshaf?.surah_list?.split(',')?.map { suraMap[it] } ?: emptyList()
        _uiState.update {
            it.copy(
                surahList = surahList,
                reciterInfo = moshaf?.reciterName ?: "",
                moshaf = moshaf!!
            )
        }
    }

    fun onSearchTextChange(text: CharSequence) {
        _uiState.update {
            it.copy(searchText = text.toString())
        }
        searchInSurahList(_uiState.value.searchText)
    }

    fun searchInSurahList(query: CharSequence) {
        val moshaf = savedStateHandle.get<MoshafEnitity>("moshaf")
        val filteredList = moshaf?.surah_list?.split(',')?.map { suraMap[it] }
            ?.filter { it?.name?.contains(query, ignoreCase = true) == true } ?: emptyList()
        _uiState.update {
            it.copy(surahList = filteredList)
        }
    }
    fun onCloseClick() {
        val moshaf = savedStateHandle.get<MoshafEnitity>("moshaf")
        val originalSurahList = moshaf?.surah_list?.split(',')?.map { suraMap[it] } ?: emptyList()
        _uiState.update {
            it.copy(
                surahList = originalSurahList,
                isTabSearchVisible = false,
                isTabTitleVisible = true,
                searchText = ""
            )
        }
    }

    fun onBackClick() {
     //   sendUiEffect(RadioUiEffect.Back)
    }

    fun onSearchClick()  {
        _uiState.update {
            it.copy(isTabSearchVisible = true , isTabTitleVisible = false)
        }
    }


}