package com.zeek1910.examples.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zeek1910.examples.App
import com.zeek1910.examples.data.repositories.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()

    private val _action = Channel<Action>(capacity = Channel.CONFLATED)
    val action: ReceiveChannel<Action> get() = _action

    fun signUp(name: String, email: String, password: String) = viewModelScope.launch {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _action.send(Action.IncorrectDataError)
            return@launch
        }
        _state.getAndUpdate { it.copy(isProgress = true) }
        val user = userRepository.signUp(name, email, password)
        if (user == null) {
            _action.send(Action.RegisterError)
        } else {
            _action.send(Action.RegisterSuccessfully)
        }
        _state.getAndUpdate { it.copy(isProgress = false) }
    }


    data class State(
        val isProgress: Boolean = false
    )

    sealed class Action {
        object RegisterError : Action()
        object IncorrectDataError : Action()
        object RegisterSuccessfully : Action()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).userRepository
                SignUpViewModel(userRepository)
            }
        }
    }
}