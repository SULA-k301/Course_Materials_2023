package com.zeek1910.databaseexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EmployerLocalDataSource(private val employerDao: EmployerDao) {

    suspend fun getAllEmployers(): List<Employer> {
        return employerDao.getAllEmployers().map { it.toModel() }
    }

    fun getAllEmployersFlow(): Flow<List<Employer>> {
        return employerDao.getAllEmployersFlow().map { it.map { item -> item.toModel() } }
    }

    suspend fun insertAll(items: List<Employer>) {
        employerDao.insertAll(items.map { it.toEntity() })
    }
}