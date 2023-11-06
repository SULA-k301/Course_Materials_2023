package com.zeek1910.examples

import android.app.Application
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.data.repositories.FakeMeditationRepository
import com.zeek1910.examples.data.repositories.MeditationRepository

class App: Application() {

    lateinit var appSettings: AppSettings
    lateinit var meditationRepository: MeditationRepository

    override fun onCreate() {
        super.onCreate()

        appSettings = AppSettings(this)
        meditationRepository = FakeMeditationRepository()
    }
}