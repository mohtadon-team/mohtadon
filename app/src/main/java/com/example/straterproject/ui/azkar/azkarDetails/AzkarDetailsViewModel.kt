package com.example.straterproject.ui.azkar.azkarDetails

import android.content.Context
import android.util.Log
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class AzkarDetailsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    fun getSpecificAzkarDetails(id: Int): ArrayList<ZekrDetailsModel> {
        val azkarDetailsArray = arrayListOf<ZekrDetailsModel>()

        try {
            val inputStream: InputStream = appContext.assets.open("azkar/azkar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()


            val azkarJsonArray: JSONArray = JSONArray(String(buffer))
            val zekrObject: JSONObject = azkarJsonArray.getJSONObject(id)
            val zekrDetailsArray = zekrObject.getJSONArray("array")
            Log.i("ahmed", zekrObject.getString("category"))
            Log.i("ahmed", zekrDetailsArray.length().toString())
            for (position in 0 until zekrDetailsArray.length()) {
                val zekrDetailsObject: JSONObject = zekrDetailsArray.getJSONObject(position)
                val zekrText = zekrDetailsObject.getString("text")
                val zekrNumberOfRepeation = zekrDetailsObject.getInt("count")
                azkarDetailsArray.add(ZekrDetailsModel(zekrText, zekrNumberOfRepeation))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return azkarDetailsArray
    }

}