package com.example.desafiopractico3.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException

object ErrorHandler {

    fun getErrorMessage(e: Exception): String {
        return when (e) {
            is HttpException -> {
                when (e.code()) {
                    401, 403 -> "Invalid API Key"
                    404 -> "Not Found"
                    in 500..599 -> "Server Error"
                    else -> "Http Error: ${e.code()}"
                }
            }
            is SocketTimeoutException -> "Request Timeout"
            else -> "Error: ${e.message}"
        }
    }
}
