package com.movies.sys_ltd_movies_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FlutterBridge.init(this)
    }
}
