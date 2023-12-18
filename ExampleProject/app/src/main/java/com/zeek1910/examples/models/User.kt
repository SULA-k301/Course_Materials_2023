package com.zeek1910.examples.models

data class User(
    val name: String,
    val email: String,
    val profileImageUrl: String? = null,
    val location: String? = null
)