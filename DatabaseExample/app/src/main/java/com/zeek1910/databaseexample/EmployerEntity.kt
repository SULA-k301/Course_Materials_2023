package com.zeek1910.databaseexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val salary: Int,
    val fullDay: Boolean
)
