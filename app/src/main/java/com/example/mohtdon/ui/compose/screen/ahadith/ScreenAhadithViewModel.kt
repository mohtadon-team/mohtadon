package com.example.mohtdon.ui.compose.screen.ahadith

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.data.hadithList
import com.example.mohtdon.ui.compose.data.topicsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenAhadithViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<AhadithUiState, AhadithScreenUiEffect>(AhadithUiState()),
    AhadithScreenInteraction {

        private val args:AhadithScreenArgs = AhadithScreenArgs(savedStateHandle)
    init {
        getData()
    }

    override fun getData() {
        val label = topicsList[args.topicsId - 1]
        Log.w("X212",label.toString())
        iState.update {
            it.copy(
                screenLabel = label.name,
                ahadith = hadithList
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(AhadithScreenUiEffect.NavigateUp)
    }

    override fun onClickSearch(value: String) {
        //TODO
    }

    override fun onToggleSearch() {
        iState.update {
            it.copy(
                isSearchVisible = !it.isSearchVisible
            )
        }
    }

    override fun onSearchValueChange(value: String) {
        iState.update {
            it.copy(
                searchValue = value
            )
        }
    }
}