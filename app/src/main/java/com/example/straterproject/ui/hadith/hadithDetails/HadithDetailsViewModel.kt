package com.example.straterproject.ui.hadith.hadithDetails

import android.content.Context
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.getInputStreambuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class HadithDetailsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    fun getHadithText(id: Int): String {
        var hadithText: String = ""

        try {

            val buffer = ("hadith/bukhari.json").getInputStreambuffer(appContext)

            val hadithJsonArray: JSONArray = JSONArray(String(buffer))
            val hadithObject: JSONObject = hadithJsonArray.getJSONObject(id)
            hadithText = hadithObject.getString("arab")

            return hadithText

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return hadithText
    }

}