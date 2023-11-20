package com.zeek1910.examples.data.repositories

import com.zeek1910.examples.models.User

class FakeUserRepository: UserRepository {
    override suspend fun isUserLogin(): Boolean {
        return true
    }

    override suspend fun signUp(userName: String, email: String, password: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(email: String, password: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}