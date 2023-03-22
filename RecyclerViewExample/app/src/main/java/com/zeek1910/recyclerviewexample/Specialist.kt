package com.zeek1910.recyclerviewexample

data class Specialist(
    val name: String,
    val profession: String,
    val imageUrl: String,
    val rating: Float,
    val reviewsCount: Int,
    val experience: Int,
    val distance: Float,
    val visitPrice: Int
) : java.io.Serializable