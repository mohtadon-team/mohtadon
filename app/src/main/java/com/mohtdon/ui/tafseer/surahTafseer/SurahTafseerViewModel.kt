package com.mohtdon.ui.tafseer.surahTafseer

import android.content.Context
import android.util.Log
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
class SurahTafseerViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {

    private val _surahTafseerUiState = MutableStateFlow(SurahTafseerUiState())
    val surahTafseerUiState = _surahTafseerUiState.asStateFlow()


    fun getSurahNameAndTafseer(position: Int) {

        var surahName: String = ""

        try {
            val inputStream: InputStream = appContext.assets.open("quran/Quran.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val QuranJsonArray = JSONArray(String(buffer))
            val SurahObject: JSONObject = QuranJsonArray.getJSONObject(position)
            surahName = SurahObject.getString("name")

            getSurahAyatText(SurahObject)


            _surahTafseerUiState.update {
                it.copy(
                    surahName = surahName
                )
            }

            getSurahTafseer(position)

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun getSurahTafseer(id: Int) {
        val SurahTafseerList = ArrayList<SurahTafseerModel>()
        try {
            val inputStream: InputStream = appContext.getAssets().open("tafseer/ar_muyassar.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()


            val surahTafseerJsonArray = JSONArray(String(buffer))
            for (position in 0 until surahTafseerJsonArray.length()) {

                val surahTafseerTextObject: JSONObject =
                    surahTafseerJsonArray.getJSONObject(position)

                if (surahTafseerTextObject.getInt("sura") == (id + 1)) {

                    Log.i("ahmed", surahTafseerTextObject.getInt("sura").toString())
                    val ayahTafseerText = surahTafseerTextObject.getString("text")
                    val ayahNumber = surahTafseerTextObject.getString("aya")

                    SurahTafseerList.add(
                        SurahTafseerModel(
                            ayahNumber, ayahTafseerText
                        )
                    )
                }
            }

            _surahTafseerUiState.update {
                it.copy(
                    surahTafseer = SurahTafseerList
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun getSurahAyatText(surahObject: JSONObject) {

        val surahAyatText = ArrayList<String>()

        val surahAyatArray = surahObject.getJSONArray("array")
        for (position in 0 until surahAyatArray.length()) {
            val ayahText = surahAyatArray.getJSONObject(position).getString("ar")
            surahAyatText.add(ayahText)
        }

        _surahTafseerUiState.update {
            it.copy(
                surahAyatText = surahAyatText
            )
        }
    }


}