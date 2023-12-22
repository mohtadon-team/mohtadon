package com.example.domain.repo

import com.example.domain.models.hadith.HadithBookChapters

//import com.example.data.dataSource.remote.response.hadith.books.HadithModel
//import com.example.data.dataSource.remote.response.hadith.chapters.HadithspecificBookChapters

interface HadithRepository {

//    suspend fun getAllHadithBooksName(): com.example.data.dataSource.remote.response.hadith.books.HadithModel
    suspend fun getSpecificHadithBookChapters(bookName:String):HadithBookChapters?
//    suspend fun getSpecifichadith(bookName:String,chapterNumber:Int , )
}