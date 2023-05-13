package com.example.postsapplication.features.posts.data.locale.typeconverter

import androidx.room.TypeConverter
import com.example.postsapplication.features.posts.data.locale.dbmodels.ResultList
import com.google.gson.Gson

class PostsListTypeConverter {

    @TypeConverter
    fun savePostsList(invoiceList: ResultList): String = Gson().toJson(invoiceList)

    @TypeConverter
    fun getPostsList(jsonString: String): ResultList =
        Gson().fromJson(jsonString, ResultList::class.java)
}