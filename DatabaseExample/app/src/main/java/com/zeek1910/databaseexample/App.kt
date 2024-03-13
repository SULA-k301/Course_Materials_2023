package com.zeek1910.databaseexample

import android.app.Application

class App : Application() {

    private var _employerLocalDataSource: EmployerLocalDataSource? = null
    val employerLocalDataSource get() = requireNotNull(_employerLocalDataSource)

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        _employerLocalDataSource = EmployerLocalDataSource(AppDatabase.getInstance().getEmployerDao())
    }
}