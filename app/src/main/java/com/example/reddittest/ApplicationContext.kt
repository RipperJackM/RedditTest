package com.example.reddittest

import android.app.Application
import android.content.Context

class ApplicationContext : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}