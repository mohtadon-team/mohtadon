package com.example.straterproject.ui.hadith.hadithList

import android.content.Context
import android.util.Log
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.getInputStreambuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HadithListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _hadithListUiState = MutableStateFlow(HadithListUiState())
    val hadithListUiState = _hadithListUiState.asStateFlow()

    init {
        collectHadith()
    }

    private fun collectHadith() {


        val prayersHadithListItems = ArrayList<String>()
        val fastingHadithListItems = ArrayList<String>()

        try {
            val buffer = ("hadith/hisnAlMuslim.json").getInputStreambuffer(appContext)
            val hadithJsonArray = JSONArray(String(buffer))
            for (position in 0 until hadithJsonArray.length()) {
                val hadithObject = hadithJsonArray.getJSONObject(position)
                when (hadithObject.getString("category")) {
                    "prayer" -> prayersHadithListItems.add(hadithObject.getString("arabic"))
                    "fasting" -> fastingHadithListItems.add(hadithObject.getString("arabic"))
                }
            }

            _hadithListUiState.update {
                it.copy(
                    prayersHadithList = prayersHadithListItems,
                    fastingHadithList = fastingHadithListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}