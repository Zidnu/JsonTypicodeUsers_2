package com.example.r504tl1973004_jsontypicodeusers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.r504tl1973004_jsontypicodeusers.domain.JsonTypiCodeAPI
import com.example.r504tl1973004_jsontypicodeusers.domain.jsonTypicodeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersScreenViewModel(private val api: JsonTypiCodeAPI) : ViewModel() {

    companion object {
        fun createFactory() : ViewModelProvider.Factory = viewModelFactory {
            initializer {

                UsersScreenViewModel(jsonTypicodeService)
            }
        }
    }
    // coin
    //
    private val _state = MutableStateFlow(UsersState())
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _state.update { currentState ->
                    currentState.copy(loading = true)
                }

                // api -> jsonTypicodeService
                val users = api.getAllUsers()
                _state.update { currentState -> currentState.copy(items = users) }

            } catch (e: Exception) {
                _state.update { currentState -> currentState.copy(error = e.message) }

            } finally {
                _state.update { currentState ->
                    currentState.copy(loading = false)
                }
            }
        }
    }

}