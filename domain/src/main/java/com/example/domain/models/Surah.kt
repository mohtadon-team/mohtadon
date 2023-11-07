package com.example.domain.entity

 data class Surah(
     val id: Int,
     val nameArabic: String?,
     val pages: List<Int>,
     val revelationPlace: String?,
     val verses_count: Int
     ){
}