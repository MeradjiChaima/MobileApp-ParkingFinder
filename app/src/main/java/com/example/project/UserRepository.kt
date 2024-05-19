package com.example.project
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File
import java.io.IOException

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

    suspend fun updateProfilePicture(file: File, userId: Int): Response<Unit> {
        try {
            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)
            return endPoint.updateProfilePicture(body, userId)
        } catch (e: Exception) {
            throw IOException("Error updating profile picture", e)
        }
    }


}

