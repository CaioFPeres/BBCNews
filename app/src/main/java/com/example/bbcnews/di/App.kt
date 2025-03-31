package com.example.bbcnews.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BBCNews : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BBCNews)
            modules(appModule)
        }
    }
}