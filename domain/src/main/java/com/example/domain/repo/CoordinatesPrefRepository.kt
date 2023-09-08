package com.example.domain.repo

import android.content.SharedPreferences

interface CoordinatesPrefRepository {
    abstract val pref: SharedPreferences
    abstract val editor: SharedPreferences.Editor

     fun putLatitude(key: String, value: String)
     fun putLongitude(key: String, value: String)
     fun getLatitude(key: String): String
     fun getLongitude(key: String): String

}