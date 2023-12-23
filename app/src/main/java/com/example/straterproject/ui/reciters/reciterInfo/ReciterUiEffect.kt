package com.example.straterproject.ui.reciters.reciterInfo

sealed interface ReciterUiEffect {
    object Back : ReciterUiEffect
    object SearchCancel : ReciterUiEffect
}