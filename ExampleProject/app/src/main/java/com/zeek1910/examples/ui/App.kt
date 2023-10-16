package com.zeek1910.examples.ui

import android.app.Application
import com.zeek1910.examples.data.AppSettings

class App: Application() {

    lateinit var appSettings: AppSettings

    override fun onCreate() {
        super.onCreate()

        appSettings = AppSettings(this)
    }
}