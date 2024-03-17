package com.mohtdon.ui.namesOfAllah.viewModels

import android.content.Context
import com.mohtdon.ui.base.BaseViewModel
import com.mohtdon.ui.namesOfAllah.NamesListUiState
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
class NamesOfAllahViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context
) : BaseViewModel() {
    private val _namesOfAllahList = MutableStateFlow(NamesListUiState())
    val namesOfAllahListItems = _namesOfAllahList.asStateFlow()

    init {
        getNamesData()
    }

    private fun getNamesData() {

        val allahNamesListItems = ArrayList<String>()
        try {
            val inputStream: InputStream = appContext.assets.open("nameOfAllah/names.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val namesJsonArray: JSONArray = JSONArray(String(buffer))
            for (position in 0..99) {
                val nameObject: JSONObject = namesJsonArray.getJSONObject(position)
                val name = nameObject.getString("name")
                val meaning = nameObject.getString("text")
                allahNamesListItems.add(name)

            }

            _namesOfAllahList.update {
                it.copy(
                    namesOfAllahList = allahNamesListItems
                )
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}