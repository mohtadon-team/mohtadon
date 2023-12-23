package com.example.data.utils

import com.example.domain.models.BadRequestException
import com.example.domain.models.NoInternetException
import com.example.domain.models.NotFoundException
import com.example.domain.models.NullResultException
import com.example.domain.models.ServerException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T> wrapApiResponse(
    request: suspend () -> Response<T>
): T {
    try {
        val response = request()
        if (response.isSuccessful) {
            return response.body() ?: throw NullResultException("No data")
        } else {
            throw when (response.code()) {
                400 -> BadRequestException(response.message())
                404 -> NotFoundException("Not found")
                else -> ServerException("Server error")
            }
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException("No Internet")
    } catch (io: IOException) {
        throw NoInternetException(io.message)
    }
}