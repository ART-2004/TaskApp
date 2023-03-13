package com.example.taskmanager.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}