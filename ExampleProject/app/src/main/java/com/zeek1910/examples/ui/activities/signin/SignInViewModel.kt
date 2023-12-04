package com.zeek1910.examples.ui.activities.signin

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

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()

    private val _action = Channel<Action>(capacity = Channel.CONFLATED)
    val action: ReceiveChannel<Action> get() = _action

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty()) {
                _action.send(Action.EmptyData)
                return@launch
            }
            _state.getAndUpdate { it.copy(isProgress = true) }
            val user = userRepository.signIn(email, password)
            if (user == null) {
                _action.send(Action.IncorrectCredentials)
            } else {
                _action.send(Action.LoginSuccessfully)
            }
            _state.getAndUpdate { it.copy(isProgress = false) }
        }
    }

    fun onEmailOrPasswordChange(email: String, password: String) {
        _state.getAndUpdate { it.copy(isSignInButtonEnable = email.isNotEmpty() && password.isNotEmpty()) }
    }

    data class State(
        val isProgress: Boolean = false,
        val isSignInButtonEnable: Boolean = false
    )

    sealed class Action {
        object IncorrectCredentials : Action()
        object LoginSuccessfully : Action()

        object EmptyData : Action()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val userRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).userRepository
                SignInViewModel(userRepository)
            }
        }
    }
}