package com.mohtdon.data.dataSource.remote.response.quran

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("number")
                val number: Int = 0,
                @SerializedName("englishName")
                val englishName: String = "",
                @SerializedName("numberOfAyahs")
                val numberOfAyahs: Int = 0,
                @SerializedName("revelationType")
                val revelationType: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("edition")
                val edition: Edition,
                @SerializedName("ayahs")
                val ayahs: List<AyahsItem>?,
                @SerializedName("englishNameTranslation")
                val englishNameTranslation: String = "")