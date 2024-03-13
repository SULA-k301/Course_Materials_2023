package com.zeek1910.databaseexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(private val employerLocalDataSource: EmployerLocalDataSource) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state.asStateFlow()


    init {
        employerLocalDataSource.getAllEmployersFlow()
            .onEach { values -> _state.getAndUpdate { it.copy(items = values) } }
            .launchIn(viewModelScope)
    }

    fun insertTestData() {
        viewModelScope.launch(Dispatchers.IO) {
            employerLocalDataSource.insertAll(
                listOf(
                    Employer(
                        firstName = "qwewqe",
                        lastName = "sadsadasd",
                        email = "sadsad",
                        salary = 1234,
                        fullDay = true
                    ),
                    Employer(
                        firstName = "qwesf",
                        lastName = "sads1213sd",
                        email = "sad213d",
                        salary = 124,
                        fullDay = true
                    ),
                    Employer(
                        firstName = "das",
                        lastName = "asfh",
                        email = "hgf",
                        salary = 21,
                        fullDay = false
                    ),
                    Employer(
                        firstName = "123dsac",
                        lastName = "bhfvdnh",
                        email = "hfdfdhfdh",
                        salary = 134,
                        fullDay = false
                    ),
                    Employer(
                        firstName = "qfsdgvfvdsge",
                        lastName = "sadsadsgsdgsdgdsgsdgdasd",
                        email = "lkjcxsr",
                        salary = 32141234,
                        fullDay = true
                    ),
                )
            )
        }

    }

    data class State(
        val items: List<Employer> = emptyList()
    )


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val employerLocalDataSource =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App).employerLocalDataSource
                MainViewModel(employerLocalDataSource)
            }
        }
    }
}