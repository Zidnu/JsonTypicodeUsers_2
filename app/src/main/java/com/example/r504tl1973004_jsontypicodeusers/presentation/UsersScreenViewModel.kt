package com.example.r504tl1973004_jsontypicodeusers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.r504tl1973004_jsontypicodeusers.domain.jsonTypicodeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersScreenViewModel : ViewModel() {
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

                val users = jsonTypicodeService.getAllUsers()
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