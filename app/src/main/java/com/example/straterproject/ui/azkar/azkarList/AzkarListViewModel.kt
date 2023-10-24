package com.example.straterproject.ui.azkar.azkarList

import android.content.Context
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
class AzkarListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _azkarListUiState = MutableStateFlow(AzkarListUiState())
    val azkarListUiState = _azkarListUiState.asStateFlow()

    init {
        collectAzkarListData()
    }

    private fun collectAzkarListData() {

        var azkarListItems = ArrayList<String>()
        try {
            val inputStream: InputStream = appContext.getAssets().open("azkar/azkar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val azkarJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0..131) {
                var zekrObject: JSONObject = azkarJsonArray.getJSONObject(position)
                var zekrName = zekrObject.getString("category")
                azkarListItems.add(zekrName)
//                Log.i("ahmed", "test")
//
//
//                Log.i("ahmed", zekrName)
            }
//            val m_jArry = obj.getJSONArray("data")
//            ayah = m_jArry.getJSONObject(randomAyahNumber).getString("text")
            _azkarListUiState.update {
                it.copy(
                    azkarList = azkarListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}