package com.zeek1910.examples.data.repositories

import com.zeek1910.examples.models.User

interface UserRepository {

    suspend fun isUserLogin(): Boolean

    suspend fun signUp(userName: String, email: String, password: String): User?

    suspend fun signIn(email: String, password: String): User

    suspend fun logout()
}