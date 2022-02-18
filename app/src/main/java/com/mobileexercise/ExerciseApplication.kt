package com.mobileexercise

import android.app.Application
import com.mobileexercise.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExerciseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidContext(this@ExerciseApplication)
            modules(appModule)
        }
    }
}