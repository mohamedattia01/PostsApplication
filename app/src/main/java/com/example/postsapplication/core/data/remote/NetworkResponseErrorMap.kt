package com.example.postsapplication.core.data.remote

import com.google.gson.Gson
import retrofit2.Response

class NetworkResponseErrorMap {
    companion object {
        /**
         * map retrofit error body
         * to RetrofitErrorModel class
         * */
        fun convertErrorBody(
            response: Response<*>?
        ): RetrofitErrorModel? {
            var error: RetrofitErrorModel? = null
            if (response?.errorBody() != null) {
                val gson = Gson()
                error = gson.fromJson(
                    response.errorBody()!!.charStream(),
                    RetrofitErrorModel::class.java
                    //
                )
            }
            return error
        }
    }
}