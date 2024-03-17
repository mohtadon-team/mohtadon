package com.mohtdon.ui.namesOfAllah.viewModels

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class NameMeaningViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {

    fun getMeaning(name: String): String {
        var meaningTxt=""
        try {
            val inputStream: InputStream = appContext.assets.open("nameOfAllah/names.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val namesJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0..99) {
                val nameObject: JSONObject = namesJsonArray.getJSONObject(position)
                val nameTxt = nameObject.getString("name")
                var meaning = nameObject.getString("text")
                if(name == nameTxt){
                  meaningTxt=meaning
                }


            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return  meaningTxt
    }
}