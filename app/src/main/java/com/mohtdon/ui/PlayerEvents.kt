package com.mohtdon.ui

import com.mohtdon.player.AudioItem


sealed class PlayerEvents {
    data class  AddPlaylist(val audios: List<AudioItem>): PlayerEvents()
    data class  AddNewAudioItem(val audio: AudioItem): PlayerEvents()
    data class GoToSpecificItem(val index:Int): PlayerEvents()
    object  PausePlay: PlayerEvents()
    object  Previous : PlayerEvents()
    object  Next : PlayerEvents()
    object  Shuffle : PlayerEvents()
    object  Repeat : PlayerEvents()
    object  SeekForward : PlayerEvents()
    object  SeekBack : PlayerEvents()
}
//   data class  SeekProgress(val value: Long): PlayerEvents()