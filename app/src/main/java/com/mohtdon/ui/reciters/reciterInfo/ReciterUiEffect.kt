package com.mohtdon.ui.reciters.reciterInfo

sealed interface ReciterUiEffect {
    object Back : ReciterUiEffect
    object SearchCancel : ReciterUiEffect
}