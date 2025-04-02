package com.example.bbcnews.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class BBCNews : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BBCNews)
            modules(appModule)
        }
    }

    override fun onTerminate(){
        super.onTerminate()
        stopKoin()
    }
}
