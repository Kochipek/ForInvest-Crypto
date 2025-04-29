package com.ipekkochisarli.forinvest_crypto.core.data.remote

import retrofit2.HttpException
import java.io.IOException

object ApiError {

    fun handleException(exception: Throwable): String {
        return when (exception) {
            is IOException -> "Network error. Please check your connection."
            is HttpException -> "Server error: ${exception.code()}"
            else -> "Unexpected error occurred."
        }
    }
}