package com.example.postsapplication.core.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

interface NetworkRemoteServiceCall {
    /**
     * safeApiCall
     * @param apiCall as suspend fn to call api
     * pass suspend api fn as parameter to safeApiCall fn
     * invoke Api at IO thread and handle logic
     * @return NetworkResponse<BaseResponse<T>>  hase success state data and failure state data
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                // invoke suspend service method
                NetworkResponse.Success(apiCall.invoke())
            } catch (throwable: Exception) {
                // check connection error
                if (throwable is IOException)
                    NetworkResponse.Failure(true, 0, null)
                else {
                    // get http response
                    val statusResponse = (throwable as? HttpException)?.response()
                    // get response code fromm http exception
                    val httpCode = statusResponse?.code()
                    // map response error to RetrofitErrorModel
                    val responseErrorModel =
                        NetworkResponseErrorMap.convertErrorBody(statusResponse)
                    NetworkResponse.Failure(false, httpCode, responseErrorModel?.reason)
                }
            }
        }
    }
}