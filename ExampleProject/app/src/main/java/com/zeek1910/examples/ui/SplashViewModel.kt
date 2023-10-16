package com.zeek1910.examples.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val _eventChannel = Channel<Event>()
    val eventChannel: ReceiveChannel<Event> get() = _eventChannel

    private val appSettings = (application as App).appSettings

    private val context = application.applicationContext

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val intent = when {
                appSettings.isFirstLaunch -> Intent(context, OnboardingActivity::class.java)
                appSettings.isUserLogin() -> Intent(context, MainActivity::class.java)
                else -> Intent(context, SignInActivity::class.java)
            }
            _eventChannel.send(Event.NavigateTo(intent))
        }
    }

    sealed class Event {
        data class NavigateTo(val intent: Intent) : Event()
    }
}