package com.example.postsapplication.core.data.remote

import com.google.gson.annotations.SerializedName

class RetrofitErrorModel {
    @SerializedName("code")
    val code: Long? = null //business code
    @SerializedName("reason")
    val reason: String? = null

}