package com.example.project

import retrofit2.Response

class UserRepository(private val endPoint: EndPoint) {
    suspend fun loginUser(email: String, password: String): Response<User> {
        val loginRequest = LoginRequest(email, password)
        return endPoint.loginUser(loginRequest)
    }
}

