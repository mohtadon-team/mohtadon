package com.example.straterproject.ui.duaa.duaaDetails

import android.content.Context
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class DuaaDetailsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    fun getDuaaText(id: Int): String {
        var duaaText: String = ""

        try {
            val inputStream: InputStream = appContext.assets.open("duaa/duaa2.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()


            val duaaJsonArray: JSONArray = JSONArray(String(buffer))
            val duaaObject: JSONObject = duaaJsonArray.getJSONObject(id)
            duaaText = duaaObject.getString("zekr")

            return duaaText

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return duaaText
    }

}