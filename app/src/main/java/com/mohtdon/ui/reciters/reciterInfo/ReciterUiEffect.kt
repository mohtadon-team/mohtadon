package com.mohtdon.mohtdon.ui.reciters.reciterInfo

sealed interface ReciterUiEffect {
    object Back : ReciterUiEffect
    object SearchCancel : ReciterUiEffect
}