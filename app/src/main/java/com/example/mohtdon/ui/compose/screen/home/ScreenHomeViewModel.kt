package com.example.mohtdon.ui.compose.screen.home

import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.ui.compose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenHomeViewModel @Inject constructor(

) : BaseViewModel<HomeUiState, HomeScreenUiEffect>(HomeUiState()), HomeScreenInteraction {


    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }

    override fun onClickMenu() {

    }

    override fun onClickPrayerFollowing() {

    }

    override fun onClickMushaf() {

    }

    override fun onCLickAzkar() {

    }

    override fun onClickDuaa() {

    }

    override fun onCLickHadith() {

    }

    override fun onCLickTafseer() {

    }

    override fun onClickMore() {

    }

    override fun onClickMoreDuaa() {

    }

    override fun onClickMoreAya() {

    }

    override fun onClickMoreAzkar() {

    }

    override fun onBottomMushaf() {

    }

    override fun onBottomFollow() {

    }

    override fun onBottomHome() {

    }

    override fun onBottomRadio() {

    }

    override fun onBottomSettings() {

    }
}