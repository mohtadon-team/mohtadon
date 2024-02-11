package com.mohtdon.data.dataSource.remote.response.quran

import com.google.gson.annotations.SerializedName

data class Edition(@SerializedName("identifier")
                   val identifier: String = "",
                   @SerializedName("englishName")
                   val englishName: String = "",
                   @SerializedName("name")
                   val name: String = "",
                   @SerializedName("format")
                   val format: String = "",
                   @SerializedName("language")
                   val language: String = "",
                   @SerializedName("type")
                   val type: String = "",
                   @SerializedName("direction")
                   val direction: String = "")