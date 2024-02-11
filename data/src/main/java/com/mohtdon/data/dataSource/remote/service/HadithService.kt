package com.mohtdon.data.dataSource.remote.service

import com.mohtdon.data.dataSource.remote.response.hadith.chapterHadith.ChapterHadithDto
import com.mohtdon.data.dataSource.remote.response.hadith.chapters.BookChaptersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HadithService {

    @GET("{bookSlug}/chapters")
    suspend fun getHadithBookChapters(
        @Path("bookSlug") bookSlug: String ,
        @Query("apiKey") apiKey: String = "$2y$10$4WAF2lNQo6TMv4T5Rb8lOP090ZHE8Bol4I1acy1nc8IdyXChTOB6"
        ): Response<BookChaptersDto>

    @GET("hadiths")
    suspend fun getChapterHadith(
        @Query("apiKey") apiKey: String = "$2y$10$4WAF2lNQo6TMv4T5Rb8lOP090ZHE8Bol4I1acy1nc8IdyXChTOB6" ,
        @Query("book") bookName:String ,
        @Query("chapter") chapterNumber:Int,
        @Query("paginate") paginate:Int = 100
    ): Response<ChapterHadithDto>
}