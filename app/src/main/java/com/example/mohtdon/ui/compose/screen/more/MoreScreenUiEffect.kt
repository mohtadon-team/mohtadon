package com.example.mohtdon.ui.compose.screen.more

import com.example.mohtdon.ui.compose.base.BaseUiEffect

sealed class MoreScreenUiEffect() : BaseUiEffect {
    data object NavigateUp : MoreScreenUiEffect()
    data object NavigateToQiblah : MoreScreenUiEffect()
    data object NavigateToAllahNames : MoreScreenUiEffect()
    data object NavigateToSebha : MoreScreenUiEffect()
    data object NavigateToShahada : MoreScreenUiEffect()
    data object NavigateToMakka : MoreScreenUiEffect()
    data object NavigateToClosestMosque : MoreScreenUiEffect()
    data object NavigateToHijryCalender : MoreScreenUiEffect()

}