package com.zeek1910.examples.ui.activities.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zeek1910.examples.App
import com.zeek1910.examples.data.AppSettings
import com.zeek1910.examples.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userRepository: UserRepository,
    private val appSettings: AppSettings
) : ViewModel() {

    private val _actionChannel = Channel<Action>()
    val actionChannel: ReceiveChannel<Action> get() = _actionChannel

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            val action = when {
                appSettings.isFirstLaunch -> Action.NavigateToOnboarding
                userRepository.isUserLogin() -> Action.NavigateToMain
                else -> Action.NavigateToSignIn
            }
            _actionChannel.send(action)
        }
    }

    sealed class Action {
        object NavigateToOnboarding : Action()
        object NavigateToMain : Action()
        object NavigateToSignIn : Action()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).userRepository
                val appSettings =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).appSettings
                SplashViewModel(userRepository, appSettings)
            }
        }
    }
}