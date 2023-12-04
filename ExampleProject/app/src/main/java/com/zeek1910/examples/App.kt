package com.zeek1910.examples

import android.app.Application
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.data.repositories.FakeMeditationRepository
import com.zeek1910.examples.data.repositories.FakeUserRepository
import com.zeek1910.examples.data.repositories.FirebaseUserRepository
import com.zeek1910.examples.data.repositories.MeditationRepository
import com.zeek1910.examples.data.repositories.UserRepository

class App: Application() {

    private var _appSettings: AppSettings? = null
    val appSettings get() = requireNotNull(_appSettings)

    private var _meditationRepository: MeditationRepository? = null
    val meditationRepository get() = requireNotNull(_meditationRepository)

    private var _userRepository: UserRepository? = null
    val userRepository get() = requireNotNull(_userRepository)

    override fun onCreate() {
        super.onCreate()

        _appSettings = AppSettings(this)
        _meditationRepository = FakeMeditationRepository()
        _userRepository = FirebaseUserRepository()
    }
}