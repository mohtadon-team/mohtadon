package com.example.mohtdon.ui.compose.screen.moshaf

import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenMoshafViewModel @Inject constructor(

) : BaseViewModel<MoshafUiState, MoshafScreenUiEffect>(MoshafUiState()), MoshafScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                items = listOf(
                    MoshafItem(
                        1, R.string.moshaf_read, R.drawable.quran_orangeicon
                    ),
                    MoshafItem(
                        2, R.string.moshaf_listen, R.drawable.headphone_orangeicon
                    ),
                    MoshafItem(
                        3, R.string.moshaf_surah, R.drawable.plant_orangeicon
                    ),
                )
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(MoshafScreenUiEffect.NavigateUp)
    }

    override fun onClickItem(id: Int) {
        when (id) {
            1 -> {
                sendUiEffect(MoshafScreenUiEffect.NavigateToRead)
            }

            2 -> {
                sendUiEffect(MoshafScreenUiEffect.NavigateToListen)
            }

            3 -> {
                sendUiEffect(MoshafScreenUiEffect.NavigateToSurahNotes)
            }
        }
    }
}