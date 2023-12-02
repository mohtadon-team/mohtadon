package com.example.straterproject.ui.duaa.duaaList

import android.content.Context
import com.example.straterproject.ui.base.BaseViewModel
import com.example.straterproject.utilities.getInputStreambuffer
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
class DuaaListViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {


    private val _duaaListUiState = MutableStateFlow(DuaaListUiState())
    val duaaListUiState = _duaaListUiState.asStateFlow()

    init {
        collectDuaaListData()
    }

    private fun collectDuaaListData() {

        val duaaListItems = ArrayList<String>()
        try {

            val buffer = ("duaa/duaa2.json").getInputStreambuffer(appContext)

            val duaaJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0..131) {
                val duaaObject: JSONObject = duaaJsonArray.getJSONObject(position)
                val duaaName = duaaObject.getString("category")
                duaaListItems.add(duaaName)

            }

            _duaaListUiState.update {
                it.copy(
                    duaaList = duaaListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    
}