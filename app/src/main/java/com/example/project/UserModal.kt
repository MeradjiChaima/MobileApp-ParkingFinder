package com.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class UserModal(private val userRepository: UserRepository) : ViewModel() {

    var user: User? by mutableStateOf(null)
        private set
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

    fun registerUser(username: String, email: String, password: String,PhoneNumber: String, param: (Boolean) -> Unit) {
        viewModelScope.launch {
            val response: Response<User> = userRepository.registerUser(RegisterRequest(username, email, password , PhoneNumber))
            if (response.isSuccessful) {
                isLoggedIn.value = true
            } else {
                isLoggedIn.value = false
            }
            param(isLoggedIn.value)
        }
    }

    fun getUserIdByEmail(email: String, param: (User?) -> Unit) {
        viewModelScope.launch {
            val response: Response<User> = userRepository.getUserIdByEmail(email)
            if (response.isSuccessful) {
                val user: User? = response.body()
                param(user)
            } else {
                param(null)
            }
        }
    }




    fun getUserById(id: Int) {
        viewModelScope.launch {
            val response: Response<User> = userRepository.getUserById(id)
            if (response.isSuccessful) {
                user = response.body()
            } else {
            }
        }
    }


}
