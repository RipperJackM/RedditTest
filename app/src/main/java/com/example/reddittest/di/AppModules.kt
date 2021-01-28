package com.example.reddittest.di

import androidx.room.Room
import com.example.reddittest.ApplicationContext.Companion.appContext
import com.example.reddittest.db.RedditDb
import org.koin.dsl.module

val applicationModule = module {

    // Database
    single {
        Room.databaseBuilder(
            appContext.applicationContext,
            RedditDb::class.java,
            RedditDb.DB_NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}