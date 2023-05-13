package com.example.postsapplication.core.data.locale.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.postsapplication.core.data.locale.roomdb.dao.PostsModelDao
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.locale.typeconverter.PostsListTypeConverter

@Database(entities = [DbPostsModel::class], exportSchema = false, version = 1)
@TypeConverters(value = [PostsListTypeConverter::class])
abstract class PostsModelDb : RoomDatabase() {
    abstract fun postsModelDao(): PostsModelDao
}