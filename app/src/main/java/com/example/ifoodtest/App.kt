package com.example.ifoodtest

import android.app.Application
import com.example.ifoodtest.di.remoteModule
import com.example.ifoodtest.weather.di.modulesWeather
import org.koin.android.ext.android.startKoin


open class App : Application() {

    private val appModules by lazy {
        listOf(remoteModule) + modulesWeather
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)
    }

}