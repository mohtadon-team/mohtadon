package com.example.straterproject.ui.tafseer.ayaTafseer

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
class AyaTafseerViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    fun getAyaTafseerText(id: Int): String {
        var ayaTafseerText: String = ""

        try {
            val inputStream: InputStream = appContext.getAssets().open("tafseer/ar_muyassar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()


            val ayaTafseerTextJsonArray: JSONArray = JSONArray(String(buffer))
            var ayaTafseerTextObject: JSONObject = ayaTafseerTextJsonArray.getJSONObject(id)
            ayaTafseerText = ayaTafseerTextObject.getString("text")

            return ayaTafseerText

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ayaTafseerText
    }

}