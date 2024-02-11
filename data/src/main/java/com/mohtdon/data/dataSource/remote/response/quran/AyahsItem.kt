package com.mohtdon.data.dataSource.remote.response.quran

import com.google.gson.annotations.SerializedName

data class AyahsItem(@SerializedName("number")
                     val number: Int = 0,
                     @SerializedName("hizbQuarter")
                     val hizbQuarter: Int = 0,
                     @SerializedName("ruku")
                     val ruku: Int = 0,
                     @SerializedName("manzil")
                     val manzil: Int = 0,
                     @SerializedName("text")
                     val text: String = "",
                     @SerializedName("page")
                     val page: Int = 0,
                     @SerializedName("sajda")
                     val sajda: Boolean = false,
                     @SerializedName("numberInSurah")
                     val numberInSurah: Int = 0,
                     @SerializedName("juz")
                     val juz: Int = 0)