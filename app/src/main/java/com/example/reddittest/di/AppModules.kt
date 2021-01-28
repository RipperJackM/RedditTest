package com.example.reddittest.di

import androidx.room.Room
import com.example.reddittest.ApplicationContext.Companion.appContext
import com.example.reddittest.db.RedditDb
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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

    // Network services
    single { HttpLoggingInterceptor() }

    single {
        OkHttpClient.Builder()
        .addInterceptor(get<HttpLoggingInterceptor>())}

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .build()
    }
}