package com.example.straterproject.ui.radio

sealed interface RadioUiEffect {
    object Back : RadioUiEffect
    object SearchCancel : RadioUiEffect
}