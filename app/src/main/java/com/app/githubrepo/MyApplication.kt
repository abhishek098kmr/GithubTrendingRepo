package com.app.githubrepo

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var sInstance: MyApplication

        @Synchronized
        fun getInstance(): MyApplication? {
            return sInstance
        }

    }


    override fun onCreate() {
        super.onCreate()
        MyApplication.sInstance = this
    }
}