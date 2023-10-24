package com.example.straterproject.ui.azkar.azkarDetails

import android.content.Context
import android.util.Log
import com.example.straterproject.ui.azkar.azkarList.AzkarListUiState
import com.example.straterproject.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        var azkarDetailsArray = arrayListOf<ZekrDetailsModel>()

        try {
            val inputStream: InputStream = appContext.getAssets().open("azkar/azkar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()


            val azkarJsonArray: JSONArray = JSONArray(String(buffer))
            var zekrObject: JSONObject = azkarJsonArray.getJSONObject(id )
            var zekrDetailsArray = zekrObject.getJSONArray("array")
            Log.i("ahmed", zekrObject.getString("category"))
            Log.i("ahmed", zekrDetailsArray.length().toString())
            for (position in 0 until zekrDetailsArray.length()) {
                var zekrDetailsObject: JSONObject = zekrDetailsArray.getJSONObject(position)
                var zekrText = zekrDetailsObject.getString("text")
                var zekrNumberOfRepeation = zekrDetailsObject.getInt("count")
                azkarDetailsArray.add(ZekrDetailsModel(zekrText , zekrNumberOfRepeation))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return azkarDetailsArray
    }

}