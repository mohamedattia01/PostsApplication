package com.example.postsapplication.core.data.remote

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
</T> */
sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorReason: String?
    ) : NetworkResponse<Nothing>()
}