package com.example.straterproject.ui.tafseer.ayatList

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
class AyatListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _ayaListUiState = MutableStateFlow(AyatListUiState())
    val ayaListUiState = _ayaListUiState.asStateFlow()

    init {
        collectDuaaListData()
    }

    private fun collectDuaaListData() {

        var ayaListItems = ArrayList<String>()
        try {
            val inputStream: InputStream = appContext.getAssets().open("tafseer/ar_muyassar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val ayaJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0 until ayaJsonArray.length()) {
                var ayaObject: JSONObject = ayaJsonArray.getJSONObject(position)
                var ayaName = ayaObject.getString("id")
                ayaListItems.add("الايه رقم $ayaName")

            }

            _ayaListUiState.update {
                it.copy(
                    ayatList = ayaListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}