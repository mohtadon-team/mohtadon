package com.example.domain.entity

data class SurahResponse(
    val code: Int,
    val `data`: List<SurahDetails>,
    val status: String
)