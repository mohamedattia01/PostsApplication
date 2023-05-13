package com.example.postsapplication.features.posts.data.locale.dbmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_posts_model")
data class DbPostsModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "result")
    val resultList: ResultList,
    @ColumnInfo(name = "total_pages") val total_pages: Int,
    val total_results: Int
)
