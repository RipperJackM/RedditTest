package com.example.reddittest

import android.app.Application
import android.content.Context
import com.example.reddittest.di.applicationModule
import org.koin.core.context.startKoin

class ApplicationContext : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            modules(listOf(applicationModule))
        }
    }
}