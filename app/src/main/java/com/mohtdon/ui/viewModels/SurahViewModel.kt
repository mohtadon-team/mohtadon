package com.mohtdon.ui.viewModels

import android.content.Context
import android.util.Log
import com.mohtdon.domain.models.quran.Surah
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.utilities.getInputStreambuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SurahViewModel @Inject constructor(
    @ApplicationContext val appContext: Context
) : BaseViewModel() {
    private val _allSura = MutableStateFlow<List<Surah>>(listOf())
    val allSura = _allSura.asStateFlow()


    init {
        fetchSurah()
    }

    fun fetchSurah() {

        val suraList = arrayListOf<Surah>()

        try {
            val buffer = ("quran/quranDetails.json").getInputStreambuffer(appContext)
            val surahListJsonObject = JSONObject(String(buffer))
            val surahListJsonArray = surahListJsonObject.getJSONArray("chapters")

            for (position in 0 until surahListJsonArray.length()) {

                val surahPages = arrayListOf<Int>()

                val surah = surahListJsonArray.getJSONObject(position)
                val surahId = surah.getInt("id")
                val surahNameInArabic = surah.getString("name_arabic")
                val pages = surah.getJSONArray("pages")

//                Log.i("ahmed", pages.toString())
                for (page in 0 until pages.length()) {
                    surahPages.add(pages.getInt(page))
                    Log.i("ahmed", pages.getInt(page).toString())
                }
                val surahRevelationPlace = surah.getString("revelation_place")
                val surahVersesCount = surah.getInt("verses_count")
                suraList.add(
                    Surah(
                        surahId,
                        surahNameInArabic,
                        surahPages,
                        surahRevelationPlace,
                        surahVersesCount
                    )
                )

            }
//            Log.i("ahmed", surahListJsonArray.toString())
//            val zekrObject: JSONObject = azkarJsonArray.getJSONObject(id)
//            val zekrDetailsArray = zekrObject.getJSONArray("array")
//            azkarDetailsArray = getJsonArrayContent(zekrDetailsArray)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        _allSura.update {
            suraList
        }
//        return azkarDetailsArray
//        viewModelScope.launch {
//            try {
//                val itemList = getAllSurahUseCase.invoke()
//                surahs.value = itemList
//            } catch (e: Exception) {
//                // Handle error
//            }
//            try {
//                surahs.postValue(UiState.Loading)
//                val itemList = getAllSurahUseCase.invoke()
//                surahs.postValue(UiState.Success(itemList))
//            } catch (e: Exception) {
//                // Handle error
//                surahs.postValue(UiState.Error(e.message.toString()))
//            }


    }
}
//}