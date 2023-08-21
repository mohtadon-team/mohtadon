package com.example.data.dataSource.remote.response.quran

import com.example.domain.entity.Surah
import com.google.gson.annotations.SerializedName

data class SurahDto(@SerializedName("code")
                         val code: Int = 0,
                    @SerializedName("data")
                         val data: List<DataItem>?,
                    @SerializedName("status")
                         val status: String = "")


