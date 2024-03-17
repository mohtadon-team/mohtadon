package com.mohtdon.ui.azkar.azkarList

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.utilities.getInputStreambuffer
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
class AzkarListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _azkarListUiState = MutableStateFlow(AzkarListUiState())
    val azkarListUiState = _azkarListUiState.asStateFlow()

    init {
        collectAzkarListData()
    }

    private fun collectAzkarListData() {

        var azkarListItems: ArrayList<String>
        try {
            val buffer = ("azkar/azkar.json").getInputStreambuffer(appContext)
            val azkarJsonArray = JSONArray(String(buffer))

            azkarListItems = getJsonArrayContent(azkarJsonArray)

            _azkarListUiState.update {
                it.copy(
                    azkarList = azkarListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getinputstreamBuffer(): ByteArray {
        val inputStream: InputStream = appContext.assets.open("azkar/azkar.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        return buffer
    }

    private fun getJsonArrayContent(azkarJsonArray: JSONArray): ArrayList<String> {
        val azkarListItems = ArrayList<String>()
        for (position in 0..131) {
            val zekrObject: JSONObject = azkarJsonArray.getJSONObject(position)
            val zekrName = zekrObject.getString("category")
            azkarListItems.add(zekrName)
        }

        return azkarListItems
    }

}