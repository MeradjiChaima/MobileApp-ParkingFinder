package com.example.project

import retrofit2.Response

class UserRepository(private val endPoint: EndPoint) {
    suspend fun loginUser(email: String, password: String): Response<User> {
        val loginRequest = LoginRequest(email, password)
        return endPoint.loginUser(loginRequest)
    }

    suspend fun registerUser(registerRequest: RegisterRequest): Response<User> {
        return endPoint.registerUser(registerRequest)
    }
    suspend fun getUserIdByEmail(email: String): Response<User> {
        return endPoint.getUserIdByEmail(email)
    }

    suspend fun getUserById(id: Int): Response<User> {
        return endPoint.getUserById(id)
    }


}

