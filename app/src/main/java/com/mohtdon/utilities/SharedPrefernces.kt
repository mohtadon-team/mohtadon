package com.mohtdon.utilities

import android.content.SharedPreferences
import javax.inject.Inject

class LastPlayedTrackPreference @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var lastPlayedTrackId: Long

        set(value) {
            val editor = sharedPreferences.edit()
            editor.putLong(KEY_LAST_PLAYED_TRACK_ID, value)
            editor.apply()
        }

        get() {
            return sharedPreferences.getLong(KEY_LAST_PLAYED_TRACK_ID, DEFAULT_VALUE)
        }



    var beforelastPlayedTrackId: Long

        set(value) {
            val editor = sharedPreferences.edit()
            editor.putLong(KEY_LAST_PLAYED_TRACK_ID, value)
            editor.apply()
        }

        get() {
            return sharedPreferences.getLong(KEY_LAST_PLAYED_TRACK_ID, DEFAULT_VALUE)
        }

    companion object {
        private const val KEY_LAST_PLAYED_TRACK_ID = "last_played_id"
        private const val KEY_BEFORE_LAST_PLAYED_TRACK_ID = "before_last_played_id"
        private const val DEFAULT_VALUE = -1L
    }

}