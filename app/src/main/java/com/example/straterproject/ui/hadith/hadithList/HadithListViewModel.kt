package com.example.straterproject.ui.hadith.hadithList

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
class HadithListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _hadithListUiState = MutableStateFlow(HadithListUiState())
    val hadithListUiState = _hadithListUiState.asStateFlow()

    init {
        collectHadithListData()
    }

    private fun collectHadithListData() {

        var hadithListItems = ArrayList<String>()
        try {
            val inputStream: InputStream = appContext.getAssets().open("hadith/bukhari.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val hadithJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0 until hadithJsonArray.length()) {
                var hadithObject: JSONObject = hadithJsonArray.getJSONObject(position)
                var hadithName = hadithObject.getString("number")
                hadithListItems.add("الحديث رقم $hadithName")

            }

            _hadithListUiState.update {
                it.copy(
                    hadithList = hadithListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}