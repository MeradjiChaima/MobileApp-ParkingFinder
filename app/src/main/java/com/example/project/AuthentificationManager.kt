package com.example.project

import android.content.Context
import android.content.SharedPreferences

class AuthentificationManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun saveCredentials(  email: String, password: String) {
        val isLoggedIn = true
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
        sharedPreferences.edit().apply {
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    fun logout() {
        sharedPreferences.edit().remove("isLoggedIn").apply()
    }
    fun getUserEmail(): String? {
        return sharedPreferences.getString("email", null)
    }
}