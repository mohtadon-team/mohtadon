package com.example.domain.models.quran

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PageData(
    @PrimaryKey(autoGenerate = false)
    var pageNumber: Int = 0,
    var soraName: String = "",
    var jozzaName: String = "",
)