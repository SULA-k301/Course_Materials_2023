package com.zeek1910.databaseexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmployerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getEmployerDao(): EmployerDao


    companion object {
        private const val DATABASE_NAME = "db"

        private var _instance: AppDatabase? = null

        fun getInstance(): AppDatabase = requireNotNull(_instance)

        fun init(context: Context) {
            _instance =
                Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}