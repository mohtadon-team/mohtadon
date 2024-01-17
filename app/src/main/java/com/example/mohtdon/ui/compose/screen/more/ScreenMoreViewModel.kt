package com.example.mohtdon.ui.compose.screen.more

import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.base.BaseViewModel
import com.example.mohtdon.ui.compose.screen.home.HomeRowItems
import com.example.mohtdon.ui.compose.theme.color_CardBackground
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenMoreViewModel @Inject constructor(

) : BaseViewModel<MoreUiState, MoreScreenUiEffect>(MoreUiState()), MoreScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                moreItems = listOf(
                    HomeRowItems(
                        1, R.drawable.qibla, R.string.more_qiblah, color_CardBackground
                    ),
                    HomeRowItems(
                        2,
                        R.drawable.names_of_allah,
                        R.string.more_allah_names,
                        color_CardBackground
                    ),
                    HomeRowItems(
                        3, R.drawable.sebha_icon, R.string.more_sebha, color_CardBackground
                    ),
                    HomeRowItems(
                        4, R.drawable.shehada_icon, R.string.more_shahada, color_CardBackground
                    ),
                    HomeRowItems(
                        5, R.drawable.qibla_blue_icon, R.string.more_makka, color_CardBackground
                    ),
                    HomeRowItems(
                        6, R.drawable.mosque, R.string.more_mosque, color_CardBackground
                    ),
                    HomeRowItems(
                        7, R.drawable.qibla_blue_icon, R.string.more_hijry, color_CardBackground
                    ),
                )
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(MoreScreenUiEffect.NavigateUp)
    }

    override fun onClickItem(id: Int) {
        when (id) {
            1 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToQiblah)

            }

            2 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToAllahNames)

            }

            3 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToSebha)

            }

            4 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToShahada)

            }

            5 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToMakka)

            }

            6 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToClosestMosque)

            }

            7 -> {
                sendUiEffect(MoreScreenUiEffect.NavigateToHijryCalender)
            }
        }
    }
}