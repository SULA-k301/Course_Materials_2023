package com.zeek1910.examples.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.zeek1910.examples.App
import com.zeek1910.examples.data.repositories.MeditationRepository
import com.zeek1910.examples.models.MeditationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MeditationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.getAndUpdate { it.copy(isProgress = true) }
            val items = repository.getData()
            _state.getAndUpdate { it.copy(isProgress = false, data = items) }
        }
    }


    data class State(
        val isProgress: Boolean = false,
        val data: List<MeditationItem> = emptyList()
    )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val meditationRepository = (this[APPLICATION_KEY] as App).meditationRepository
                HomeViewModel(meditationRepository)
            }
        }
    }
}