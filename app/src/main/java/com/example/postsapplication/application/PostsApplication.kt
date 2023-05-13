package com.example.postsapplication.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}