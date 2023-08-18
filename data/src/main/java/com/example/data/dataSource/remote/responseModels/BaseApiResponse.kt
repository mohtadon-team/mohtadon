package com.example.data.dataSource.remote.responseModels

data class BaseApiResponse<T>(var code: Int, var status: String, var data: T?)