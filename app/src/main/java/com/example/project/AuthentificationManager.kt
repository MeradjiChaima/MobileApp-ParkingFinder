package com.example.project

import android.content.Context
import android.content.SharedPreferences

class AuthentificationManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun login(email: String, password: String): Boolean {
        val isLoggedIn = true
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
        return isLoggedIn
    }

    fun logout() {
        sharedPreferences.edit().remove("isLoggedIn").apply()
    }
}