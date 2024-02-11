package com.mohtdon.data.dataSource.remote.response.quran

import com.google.gson.annotations.SerializedName

data class AyahDto(@SerializedName("code")
                        val code: Int = 0,
                   @SerializedName("data")
                        val data: Data,
                   @SerializedName("status")
                        val status: String = "")