package com.example.data.dataSource.remote.service

import com.example.data.dataSource.remote.response.hadith.chapters.BookChaptersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HadithService {

    @GET("{bookSlug}/chapters")
    suspend fun getHadithBookChapters(
        @Path("apiKey ") apiKey: String = "\$2\$10\$4WAF2lNQo6TMv4T5Rb8lOP090ZHE8Bol4I1acy1nc8IdyXChTOB6",
        @Query("bookSlug") bookSlug: String = "abu-dawood"
        ): Response<BookChaptersDto>


}