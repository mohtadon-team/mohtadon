package com.mohtdon.data.dataSource.repository

import android.content.Context
import android.content.SharedPreferences
import com.mohtdon.domain.repo.CoordinatesPrefRepository

class CoordinatesPrefRepositoryImp(context: Context) : CoordinatesPrefRepository {
    override val pref: SharedPreferences =
        context.getSharedPreferences("sharedpreference.txt", Context.MODE_PRIVATE)

    override val editor: SharedPreferences.Editor = pref.edit()

    override  fun putString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override  fun getString(key: String , defaultValue:String ): String {
        return pref.getString(key, defaultValue)!!
    }

}