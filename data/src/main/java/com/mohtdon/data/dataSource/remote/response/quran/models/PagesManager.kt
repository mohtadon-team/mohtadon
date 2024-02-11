package com.mohtdon.data.dataSource.remote.response.quran.models

import android.content.Context
import android.graphics.drawable.Drawable
import java.io.IOException
import java.io.InputStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class PagesManager {
    companion object {

        fun getQuranImageByPageNumber(context: Context, pageNumber: Int): Drawable? {
            val formatter = DecimalFormat("000")
            val symbols = DecimalFormatSymbols(Locale.US)
            formatter.decimalFormatSymbols = symbols
            val drawableName = "quran/images/page" + formatter.format(pageNumber) + ".png"
            var istr: InputStream? = null
            try {
                istr = context.assets.open(drawableName)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return Drawable.createFromStream(istr, null)
        }
//            val drawableName = "page" + formatter.format(pageNumber)
//            Log.d("name", drawableName)
//            return context.resources.getIdentifier(drawableName,"drawable",context.packageName)

    }
}