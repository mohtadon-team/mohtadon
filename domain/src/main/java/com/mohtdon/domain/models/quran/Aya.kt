package com.mohtdon.domain.models.quran

import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "quran")
    data class Aya (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val jozz: Int,
        val sora: Int,
        val sora_name_en: String,
        val sora_name_ar: String,
        val page: Int,
        val line_start: Int,
        val line_end: Int,
        val aya_no: Int,
        val aya_text: String,
        val aya_text_emlaey: String
    )

