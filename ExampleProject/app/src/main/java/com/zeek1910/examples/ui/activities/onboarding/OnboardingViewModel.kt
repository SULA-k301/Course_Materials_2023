package com.zeek1910.examples.ui.activities.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zeek1910.examples.App
import com.zeek1910.examples.data.AppSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class OnboardingViewModel(private val appSettings: AppSettings) : ViewModel() {

    private val _event = Channel<Event>(capacity = Channel.CONFLATED)
    val event: ReceiveChannel<Event> get() = _event

    fun markOnboardingAsCompleted() = viewModelScope.launch(Dispatchers.IO) {
        appSettings.isShowOnboarding = false
        _event.send(Event.MoveToNextStep)
    }

    sealed class Event {
        object MoveToNextStep : Event()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val appSettings =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).appSettings
                OnboardingViewModel(appSettings)
            }
        }
    }

}