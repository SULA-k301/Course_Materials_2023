package com.zeek1910.databaseexample

fun EmployerEntity.toModel() = Employer(
    firstName = firstName,
    lastName = lastName,
    email = email,
    salary = salary,
    fullDay = fullDay
)

fun Employer.toEntity() = EmployerEntity(
    firstName = firstName,
    lastName = lastName,
    email = email,
    salary = salary,
    fullDay = fullDay
)