package com.example.straterproject.ui.radio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.radio.RadioEntity
import com.example.domain.entity.reciters.ReciterEntity
import com.example.domain.usecases.GetAllRadioStation
import com.example.domain.usecases.GetAllReciterUseCase
import com.example.straterproject.ui.UiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RadioViewModel @Inject constructor(
    private val getAllRadioStation: GetAllRadioStation
): BaseViewModel() {

    private var _radioStations = MutableLiveData<UiState<List<RadioEntity>>>()
    val  radioStations : LiveData<UiState<List<RadioEntity>>> = _radioStations


    init{
        getAllRadioStation()
    }

    private fun getAllRadioStation() = viewModelScope.launch {
        try {
            _radioStations.postValue(UiState.Loading)
            val result = getAllRadioStation.invoke()
            _radioStations.postValue(UiState.Success(result))

        }catch (e:Exception){
            _radioStations.postValue(UiState.Error(e.message.toString()))
        }
    }
}