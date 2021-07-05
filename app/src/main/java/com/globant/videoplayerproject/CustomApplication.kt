package com.globant.videoplayerproject

import dagger.hilt.android.HiltAndroidApp
import android.app.Application

@HiltAndroidApp
class CustomApplication : Application() {

    companion object {
        private lateinit var instance: CustomApplication
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}