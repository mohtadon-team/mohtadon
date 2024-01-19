package com.example.mohtdon.ui.compose.screen.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateHandle
import com.example.mohtdon.MyApplication
import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.data.homeRowItems
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor1
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor2
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor3
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor4
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor5
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor6
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenHomeViewModel @Inject constructor(
    private val application: Application
) : BaseViewModel<HomeUiState, HomeScreenUiEffect>(HomeUiState()), HomeScreenInteraction {


    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                todayDate = ContextCompat.getString(
                    application.applicationContext,
                    R.string.hijry_date
                ),
                todayDuaa = ContextCompat.getString(
                    application.applicationContext,
                    R.string.today_duaa
                ),
                todayAya = ContextCompat.getString(
                    application.applicationContext,
                    R.string.today_aya
                ),
                todayAzkar = ContextCompat.getString(
                    application.applicationContext,
                    R.string.today_azkar
                ),
                nextPrayer = "المغرب 6:54 م",
                homeItems = homeRowItems
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(HomeScreenUiEffect.NavigateUp)
    }

    override fun onClickMenu() {
        sendUiEffect(HomeScreenUiEffect.NavigateToMenu)
    }

    override fun onClickPrayerFollowing() {
        sendUiEffect(HomeScreenUiEffect.NavigateToPrayerFollowing)
    }

    override fun onClickMoreDuaa() {
        sendUiEffect(HomeScreenUiEffect.NavigateToMoreDuaa)
    }

    override fun onClickMoreAya() {
        sendUiEffect(HomeScreenUiEffect.NavigateToMoreAya)
    }

    override fun onClickHomeRowItem(id: Int) {
        when (id) {
            1 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToMoshaf)
            }

            2 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToAzkar)

            }

            3 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToDuaa)

            }

            4 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToHadith)
            }

            5 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToTafseer)
            }

            6 -> {
                sendUiEffect(HomeScreenUiEffect.NavigateToMore)
            }

            else -> {}
        }
    }

    override fun onClickMoreAzkar() {
        sendUiEffect(HomeScreenUiEffect.NavigateToAzkar)
    }
}