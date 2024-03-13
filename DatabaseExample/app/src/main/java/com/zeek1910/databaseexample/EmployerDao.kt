package com.zeek1910.databaseexample

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployerDao {

    @Query("SELECT * FROM EmployerEntity")
    suspend fun getAllEmployers(): List<EmployerEntity>

    @Query("SELECT * FROM EmployerEntity")
    fun getAllEmployersFlow(): Flow<List<EmployerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<EmployerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: EmployerEntity)

    @Query("SELECT * FROM EmployerEntity WHERE salary > :salary")
    suspend fun getEmployersWithSalaryBiggestThen(salary: Int): List<EmployerEntity>


}