package com.mohtdon.mohtdon.ui.radio

sealed interface RadioUiEffect {
    object Back : RadioUiEffect
    object SearchCancel : RadioUiEffect
}