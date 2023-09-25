package com.zeek1910.viewexamples

import android.content.Context

class AppSettings(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(user: User) {
        sharedPref.edit()
            .putString(KEY_USER_NAME, user.name)
            .putString(KEY_USER_EMAIL, user.email)
            .putString(KEY_USER_PASSWORD, user.password)
            .apply()
    }

    fun getSavedUser(): User? {
        val name = sharedPref.getString(KEY_USER_NAME, null)
        val email = sharedPref.getString(KEY_USER_EMAIL, null)
        val password = sharedPref.getString(KEY_USER_PASSWORD, null)
        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()){
            return null
        }
        return User(name, email, password)
    }

    fun isUserLogin(): Boolean {
        return sharedPref.getBoolean(KEY_IS_LOGIN, false)
    }

    fun setIsLoginSuccess() {
        sharedPref.edit().putBoolean(KEY_IS_LOGIN, true).apply()
    }

    fun logout() {
        sharedPref.edit().putBoolean(KEY_IS_LOGIN, false).apply()
    }

    companion object {
        private const val PREF_NAME = "settings"
        private const val KEY_IS_LOGIN = "key_is_login"

        private const val KEY_USER_NAME = "key_user_name"
        private const val KEY_USER_EMAIL = "key_user_email"
        private const val KEY_USER_PASSWORD = "key_user_password"
    }
}