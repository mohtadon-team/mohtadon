package com.example.data.dataSource.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repo.CoordinatesPrefRepository

class CoordinatesPrefRepositoryImp(context: Context) : CoordinatesPrefRepository {
    override val pref: SharedPreferences =
        context.getSharedPreferences("sharedpreference.txt", Context.MODE_PRIVATE)

    override val editor: SharedPreferences.Editor = pref.edit()

    override  fun putLatitude(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override  fun putLongitude(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override  fun getLatitude(key: String): String {
        return pref.getString(key, "")!!
    }

    override  fun getLongitude(key: String): String {
        return pref.getString(key, "")!!
    }
}