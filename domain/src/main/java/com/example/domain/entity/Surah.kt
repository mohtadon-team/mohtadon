package com.example.domain.entity

 data class Surah(
     val id: Int,
     val nameArabic: String?,
     val revelationPlace: String?,
     val pages: List<Int>,

     ){
}