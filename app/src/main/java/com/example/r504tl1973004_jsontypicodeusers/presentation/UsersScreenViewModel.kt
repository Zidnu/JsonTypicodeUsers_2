package com.example.r504tl1973004_jsontypicodeusers.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.r504tl1973004_jsontypicodeusers.domain.CreateUserReqDto
import com.example.r504tl1973004_jsontypicodeusers.domain.JsonTypiCodeAPI
import com.example.r504tl1973004_jsontypicodeusers.domain.jsonTypicodeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class UsersScreenViewModel(private val api: JsonTypiCodeAPI) : ViewModel() {

    companion object {
        fun createFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {

                UsersScreenViewModel(jsonTypicodeService)
            }
        }

    }

    private val _state = MutableStateFlow(UsersState())
    val state = _state.asStateFlow()

    private val _addUserState = MutableStateFlow(AddUserState())
    val addUserState = _addUserState.asStateFlow()

    init {
        Log.d("juhanikikkailee::viewmodel", "${hashCode()}")
        getUsers()
    }

    fun createUser() {
        viewModelScope.launch {
            try {
                val newUser = api.createUser(CreateUserReqDto(email = addUserState.value.email))
                // HUOM tämä ei tomi niin kuin voisi luulla
                _state.update { currentState ->
                    currentState.copy(items=state.value.items + newUser)
                }
            } catch (e: Exception) {
            } finally {
            }
        }
    }

    fun updateEmail(newEmail: String) {
        _addUserState.update { currentState -> currentState.copy(email = newEmail) }
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                _state.update { currentState ->
                    currentState.copy(loading = true)

                }

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