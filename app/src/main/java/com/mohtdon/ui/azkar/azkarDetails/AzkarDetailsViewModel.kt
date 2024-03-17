package com.mohtdon.ui.azkar.azkarDetails

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.utilities.getInputStreambuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AzkarDetailsViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    fun getSpecificAzkarDetails(id: Int): ArrayList<ZekrDetailsModel> {
        var azkarDetailsArray = arrayListOf<ZekrDetailsModel>()

        try {
            val buffer = ("azkar/azkar.json").getInputStreambuffer(appContext)
            val azkarJsonArray = JSONArray(String(buffer))
            val zekrObject: JSONObject = azkarJsonArray.getJSONObject(id)
            val zekrDetailsArray = zekrObject.getJSONArray("array")
            azkarDetailsArray = getJsonArrayContent(zekrDetailsArray)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return azkarDetailsArray
    }


    private fun getJsonArrayContent(zekrDetailsArray: JSONArray): ArrayList<ZekrDetailsModel> {
        val azkarDetailsArray = arrayListOf<ZekrDetailsModel>()
        for (position in 0 until zekrDetailsArray.length()) {
            val zekrDetailsObject: JSONObject = zekrDetailsArray.getJSONObject(position)
            val zekrText = zekrDetailsObject.getString("text")
            val zekrNumberOfRepeation = zekrDetailsObject.getInt("count")
            azkarDetailsArray.add(ZekrDetailsModel(zekrText, zekrNumberOfRepeation))
        }

        return azkarDetailsArray
    }
}