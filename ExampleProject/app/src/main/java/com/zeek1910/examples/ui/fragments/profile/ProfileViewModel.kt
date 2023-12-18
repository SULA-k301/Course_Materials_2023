package com.zeek1910.examples.ui.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zeek1910.examples.App
import com.zeek1910.examples.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()

    private val _event = Channel<Event>(capacity = Channel.CONFLATED)
    val event: ReceiveChannel<Event> get() = _event

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getCurrentUser()
            _state.getAndUpdate {
                it.copy(
                    profileImageUrl = user.profileImageUrl,
                    profileName = user.name,
                    profileLocation = user.location
                )
            }
        }

    }

    fun onLogoutClick() = viewModelScope.launch(Dispatchers.IO) {
        _state.getAndUpdate { it.copy(isProgress = true) }
        userRepository.logout()
        _state.getAndUpdate { it.copy(isProgress = false) }
        _event.send(Event.GoToSignInScreen)
    }

    data class State(
        val isProgress: Boolean = false,
        val profileImageUrl: String? = null,
        val profileName: String = "",
        val profileLocation: String? = null
    )

    sealed class Event {
        object GoToSignInScreen : Event()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).userRepository
                ProfileViewModel(userRepository)
            }
        }
    }
}