package com.mohtdon.domain.repo

import android.content.SharedPreferences

interface CoordinatesPrefRepository {
    abstract val pref: SharedPreferences
    abstract val editor: SharedPreferences.Editor

     fun putString(key: String, value: String)
     fun getString(key: String , defaultValue:String): String

}