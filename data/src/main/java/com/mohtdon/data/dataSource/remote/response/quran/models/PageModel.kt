package com.mohtdon.data.dataSource.remote.response.quran.models

class PageModel(
    var per_page: Int,
    val pageNumber: Int ,
    var current_page: Int,
    var next_page: Int,
    var total_pages: Int,
    var total_records: Int
) {
}