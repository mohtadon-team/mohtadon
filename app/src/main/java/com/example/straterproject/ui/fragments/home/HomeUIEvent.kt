package com.example.straterproject.ui.fragments.home

sealed interface HomeUIEvent {

    object ClickSeeAllPrayerTimes: HomeUIEvent
    object ClickGetQibla: HomeUIEvent
    object ClickMuteNextSalahVoice: HomeUIEvent
    object ClickMarkPerformTheCurrentSalah: HomeUIEvent

}