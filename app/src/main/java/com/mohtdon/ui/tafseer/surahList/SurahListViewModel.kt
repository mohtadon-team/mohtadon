package com.mohtdon.ui.tafseer.surahList

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
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
class SurahListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context,
) : BaseViewModel() {


    private val _surahListUiState = MutableStateFlow(SurahListUiState())
    val surahListUiState = _surahListUiState.asStateFlow()

    init {
        collectSurahListData()
    }

    private fun collectSurahListData() {


        val surahListItems = ArrayList<SurahModel>()
        try {
            val inputStream: InputStream = appContext.assets.open("quran/Quran.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val QuranJsonArray = JSONArray(String(buffer))
            for (position in 0 until QuranJsonArray.length()) {
                val SurahObject: JSONObject = QuranJsonArray.getJSONObject(position)
                val surahId = SurahObject.getString("id")
                val surahName = SurahObject.getString("name")
                val surahRevelationPlace = SurahObject.getString("type")
                val surahNumberOfAyat = SurahObject.getJSONArray("array").length()
                surahListItems.add(
                    SurahModel(
                        surahId.toInt(), surahName, surahRevelationPlace, surahNumberOfAyat
                    )
                )

            }

            _surahListUiState.update {
                it.copy(
                    surahList = surahListItems
                )

            }


        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
