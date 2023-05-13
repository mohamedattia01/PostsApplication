package com.example.postsapplication.core.data.locale.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel

@Dao
interface PostsModelDao {

    @Query("Select * from popular_posts_model")
    suspend fun getPostsModelList(): List<DbPostsModel>

    @Insert
    suspend fun insertResult(popularPostsModel: DbPostsModel)

    @Update
    suspend fun updateResult(popularPostsModel: DbPostsModel)

    @Delete
    suspend fun deleteResult(popularPostsModel: DbPostsModel)
}