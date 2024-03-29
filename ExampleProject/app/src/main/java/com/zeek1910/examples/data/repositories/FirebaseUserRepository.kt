package com.zeek1910.examples.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.zeek1910.examples.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

class FirebaseUserRepository : UserRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun isUserLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signUp(userName: String, email: String, password: String): User? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (result.user != null) {
                User("", email)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

    }

    override suspend fun signIn(email: String, password: String): User? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (result != null) {
                User("", email)
            } else {
                null
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            null
        }

    }

    override suspend fun logout() {
        delay(500)
        firebaseAuth.signOut()
    }

    override suspend fun getCurrentUser(): User {
        return User(
            "Afreen Khan",
            "",
            "https://s.hdnux.com/photos/51/23/24/10827008/4/1200x0.jpg",
            "Lucknow, India"
        )
    }
}