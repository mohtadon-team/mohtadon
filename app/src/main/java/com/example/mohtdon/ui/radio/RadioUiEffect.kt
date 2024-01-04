package com.example.mohtdon.ui.radio

sealed interface RadioUiEffect {
    object Back : RadioUiEffect
    object SearchCancel : RadioUiEffect
}