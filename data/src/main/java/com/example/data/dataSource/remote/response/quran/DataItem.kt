package com.example.data.dataSource.remote.response.quran

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("number")
                    val number: Int = 0,
                    @SerializedName("englishName")
                    val englishName: String = "",
                    @SerializedName("numberOfAyahs")
                    val numberOfAyahs: Int = 0,
                    @SerializedName("revelationType")
                    val revelationType: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("englishNameTranslation")
                    val englishNameTranslation: String = "")