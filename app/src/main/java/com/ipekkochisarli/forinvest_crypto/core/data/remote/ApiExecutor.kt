package com.ipekkochisarli.forinvest_crypto.core.data.remote

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ApiExecutor {

    fun <T> executeCall(call: Call<T>, onResult: (ApiResult<T>) -> Unit) {

        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(ApiResult.Success(it))
                    } ?: onResult(ApiResult.Error("Empty response body"))
                } else {
                    onResult(ApiResult.Error("Server error: ${response.code()}", response.code()))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val errorMessage = ApiError.handleException(t)
                onResult(ApiResult.Error(errorMessage))
            }
        })
    }
}
