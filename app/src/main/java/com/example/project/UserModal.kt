package com.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserModal(private val userRepository: UserRepository) : ViewModel() {
    var isLoggedIn = mutableStateOf(false)
        private set

    fun loginUser(email: String, password: String, param: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response: Response<User> = userRepository.loginUser(email, password)
            if (response.isSuccessful) {
                isLoggedIn.value = true
            } else {
                isLoggedIn.value = false
            }
            param(isLoggedIn.value)
        }
    }
}
